// Alex Kiss Lab 4 adding to lab 3 
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Use SwingUtilities to so that GUI updates are done on the thread
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Data Visualization");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            // Create a CSVReader object to read data from a CSV file
            CSVReader csvReader = new CSVReader();
            String filePath = "Data/data.csv"; // Path to the CSV file containing the data
            // Read the data from the CSV file and store it in a list of Item objects
            List<Item> items = csvReader.readCSV(filePath);

            // Create a custom chart panel to visualize the data
            CustomChartPanel chartPanel = new CustomChartPanel(items);
            // Create a panel to show details of the selected item
            DetailsPanel detailsPanel = new DetailsPanel();
            // Create a panel to show stats data
            StatsPanel statsPanel = new StatsPanel();
            // Create a data panel to display a table of items and link it to the details and stats panels
            DataPanel dataPanel = new DataPanel(items, detailsPanel, statsPanel);

            // Calculate and display initial stats
            statsPanel.displayStats(items); // Show the averages for the data

            // formats of the frames
            frame.add(chartPanel, BorderLayout.CENTER);
            frame.add(dataPanel, BorderLayout.WEST);
            frame.add(detailsPanel, BorderLayout.EAST);
            frame.add(statsPanel, BorderLayout.SOUTH);

            // put details in a frame matching their size
            frame.pack();
            // Make the frame visible on the screen
            frame.setVisible(true);
        });
    }
}
