package paczka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DataProcessor {

    private CsvFileReader csvFileReader;
    private String[][] dataArray;
    private String[] headers;
//    private String[] filteredFileLines;

    public DataProcessor(CsvFileReader fileReader){
        csvFileReader = fileReader;
        fillDataArray();
        prepareHeaders();
        removeHeadersFromData();
    }


    public DataProcessor(CsvFileReader fileReader, String filter){
        csvFileReader = fileReader;
        fillDataArray();
        filterHeaders();
        filterDataForTable();
        removeHeadersFromData();

    }

    // Fills dataArray array
    protected void fillDataArray() {
        String[] fileLines = filterFileLines(csvFileReader.getFileLines());
        System.out.println(fileLines.length);
        String[][] data = new String[fileLines.length][8];
        for (int i = 0; i < fileLines.length; i++) {
            String[] temp = fileLines[i].split(",");
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = temp[j];
            }
        }
        setDataArray(data);
    }

    protected String[] filterFileLines(String[] fileLines){
        List<String> filteredFileLines = new ArrayList<String>();
        filteredFileLines.add(fileLines[0]);
        for (int i=1; i<fileLines.length;i++){
            if(fileLines[i].contains("bizhub")){
                filteredFileLines.add(fileLines[i]);
            }
        }
        return filteredFileLines.toArray(new String[filteredFileLines.size()]);
    }
    protected void prepareHeaders(){
        String[] headers = new String[dataArray[0].length];
        for (int i=0; i<dataArray[0].length; i++){
            headers[i] = dataArray[0][i];
        }
        setHeaders(headers);
    }

    protected void removeHeadersFromData(){
        String [][] temp = new String [dataArray.length-1][dataArray[0].length];
        for (int i=1; i<dataArray.length; i++){
            for (int j =0; j<dataArray[0].length; j++){
                temp [i-1][j] = dataArray[i][j];
            }
        }
        setDataArray(temp);
    }

    // Get filtered lines from file based on filter
    protected void sortArrayByColumn(String[][] array, int column) {
        Arrays.sort(array, new Comparator<String[]>() {
                    @Override
                    public int compare(String[] first, String[] second) {
                        // compare the first element
                        if (first[column] == second[column]) {
                            return 0;
                        }
                        if (first[column] == null) {
                            return -1;
                        }
                        if (second[column] == null) {
                            return 1;
                        }
                        int comparedTo = first[column].compareTo(second[column]);

                        if (comparedTo == 0) return first[1].compareTo(second[1]);
                        else return comparedTo;
                    }

                }
        );
    }

// Prepares filtered headers for table take only device name, serial number, misc supply and level
    protected void filterHeaders() {
       String filteredHeaders[] = new String[4];

       filteredHeaders[0] = headers[1];
       filteredHeaders[1] = headers[2];
       filteredHeaders[2] = headers[6];
       filteredHeaders[3] = headers[7];

       setHeaders(filteredHeaders);
       
    }


    // Fill dataArray to get device, serial number, misc supply level and level

    protected void filterDataForTable() {
        String[][] filteredData = new String[dataArray.length][4];
        for (int i = 0; i < dataArray.length; i++) {
            filteredData[i][3] = dataArray[i][7];
            filteredData[i][0] = dataArray[i][1];
            filteredData[i][1] = dataArray[i][2];
            filteredData[i][2] = dataArray[i][6];
        }
        setDataArray(filteredData);
    }

    public String[][] getDataArray() {
        return dataArray;
    }

    public void setDataArray(String[][] dataArray) {
        this.dataArray = dataArray;
    }

    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }
}



