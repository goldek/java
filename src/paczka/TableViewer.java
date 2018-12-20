package paczka;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import org.jdesktop.swingx.JXTable;

public class TableViewer extends JPanel {
    private JXTable table;

    public TableViewer(String[] columnNames, String[][] data){
        super(new GridLayout(1,0));
        setTable(new JXTable(data, columnNames));
        table.setPreferredScrollableViewportSize(new Dimension(700,700));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);

    }

    private static void createAndShowGUI() throws IOException{
        JFrame frame = new JFrame("Poziom materiałów Pilkington");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CsvFileReader csvReader = new CsvFileReader("Testowy2.csv");
        DataProcessor dataProcessor = new DataProcessor(csvReader);
//        dataProcessor.prepareHeadersForTable();

        String[] headers = dataProcessor.getHeaders();
        String [][] data = dataProcessor.getDataArray();

        TableViewer newContentPane = new TableViewer(headers, data);

        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        frame.pack();
        frame.setVisible(true);
    }

    public JXTable getTable() {
        return table;
    }

    public void setTable(JXTable table) {
        this.table = table;
    }

    public static void main(String[] args) {

        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
