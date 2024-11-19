import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StatsPanel extends JPanel {
    private JTable statsTable; // Table to display statistics
    private DefaultTableModel statsModel; // model to manage data for my stats

    // Constructor for stats panel
    public StatsPanel() {
        setLayout(new BorderLayout()); // Use BorderLayout for organizing components
        String[] columnNames = {"Year", "Average Value"}; // Define column names for the table
        statsModel = new DefaultTableModel(columnNames, 0); // Create a table model with the column names
        statsTable = new JTable(statsModel); // Create a table using the model
        add(new JScrollPane(statsTable), BorderLayout.CENTER); // Add the table to the panel with scroll capability
    }

    // Method to display statistics based on a list of items
    public void displayStats(List<Item> items) {
        // Clear existing data in the stats table
        statsModel.setRowCount(0);

        // Check if the list of items is empty
        if (items.isEmpty()) {
            System.out.println("No data available for averaging."); // Print message if no data
            return; // Exit the method if there are no items
        }

        // Variables to hold the sum of values for each year
        double sum2015 = 0, sum2016 = 0, sum2017 = 0, sum2018 = 0, sum2019 = 0, sum2020 = 0;
        // Counters to keep track of how many valid values there are for each year
        int count2015 = 0, count2016 = 0, count2017 = 0, count2018 = 0, count2019 = 0, count2020 = 0;

        // Loop through each item to calculate sums and counts
        for (Item item : items) {
            // Sum and count only if the value is valid (non-negative)
            if (item.getValue2015() >= 0) {
                sum2015 += item.getValue2015(); // Add value to sum
                count2015++; // Increment count
            }
            if (item.getValue2016() >= 0) {
                sum2016 += item.getValue2016(); // Add value to sum
                count2016++; // Increment count
            }
            if (item.getValue2017() >= 0) {
                sum2017 += item.getValue2017(); // Add value to sum
                count2017++; // Increment count
            }
            if (item.getValue2018() >= 0) {
                sum2018 += item.getValue2018(); // Add value to sum
                count2018++; // Increment count
            }
            if (item.getValue2019() >= 0) {
                sum2019 += item.getValue2019(); // Add value to sum
                count2019++; // Increment count
            }
            if (item.getValue2020() >= 0) {
                sum2020 += item.getValue2020(); // Add value to sum
                count2020++; // Increment count
            }
        }

        // Calculate averages for each year; if no valid values, set average to 0
        double avg2015 = count2015 > 0 ? sum2015 / count2015 : 0;
        double avg2016 = count2016 > 0 ? sum2016 / count2016 : 0;
        double avg2017 = count2017 > 0 ? sum2017 / count2017 : 0;
        double avg2018 = count2018 > 0 ? sum2018 / count2018 : 0;
        double avg2019 = count2019 > 0 ? sum2019 / count2019 : 0;
        double avg2020 = count2020 > 0 ? sum2020 / count2020 : 0;

        // Add the calculated averages to the stats table
        statsModel.addRow(new Object[]{"2015", avg2015}); 
        statsModel.addRow(new Object[]{"2016", avg2016});
        statsModel.addRow(new Object[]{"2017", avg2017});
        statsModel.addRow(new Object[]{"2018", avg2018});
        statsModel.addRow(new Object[]{"2019", avg2019});
        statsModel.addRow(new Object[]{"2020", avg2020});
    }


    public void update(List<Item> filteredItems) {
        displayStats(filteredItems); // Recalculate and update the stats
    }
}

