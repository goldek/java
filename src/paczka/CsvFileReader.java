package paczka;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvFileReader extends FileReader {
    private String fileName;
    private String[] fileLines;

    public CsvFileReader(String fileName) throws IOException {
        super(fileName);
        getLinesFromFile();

    }



    //    Reads file and return List of lines
    protected void getLinesFromFile() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(this);
        List<String> csvLines = new ArrayList<String>();
        String textline = bufferedReader.readLine();
        do {
            csvLines.add(textline);
            textline = bufferedReader.readLine();
        } while (textline != null);
        this.close();
        setFileLines(csvLines.toArray(new String[csvLines.size()]));
    }



    public String[] getFileLines() {
        return fileLines;
    }

    public void setFileLines(String[] fileLines) {
        this.fileLines = fileLines;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}


