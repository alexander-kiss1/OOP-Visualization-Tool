import javax.swing.*;
import java.awt.*;
import java.util.List;

// Implements Observer to listen to updates from DataPanel
public class DetailsPanel extends JPanel implements Observer {
    private JTextArea detailsArea; // Area to display details about the selected item

    // Constructor to set up the details panel
    public DetailsPanel() {
        setLayout(new BorderLayout());
        detailsArea = new JTextArea(10, 30);
        detailsArea.setEditable(false);
        add(new JScrollPane(detailsArea), BorderLayout.CENTER);
    }

    // Method to display details
    public void displayItemDetails(Item item) {
        // Build a string with details of the item
        String details = "Country Name: " + item.getCountryName() + "\n" +
                "Country Code: " + item.getCountryCode() + "\n" +
                "Data Name: " + item.getDataName() + "\n" +
                "2015: " + item.getValue2015() + "\n" +
                "2016: " + item.getValue2016() + "\n" +
                "2017: " + item.getValue2017() + "\n" +
                "2018: " + item.getValue2018() + "\n" +
                "2019: " + item.getValue2019() + "\n" +
                "2020: " + item.getValue2020();
        detailsArea.setText(details);
    }

    // Observer's update method to update the details panel when data changes
    @Override
    public void update(List<Item> filteredItems) {
        // Clear previous details
        detailsArea.setText("");

        // If no items are selected or filtered, display a message
        if (filteredItems.isEmpty()) {
            detailsArea.setText("No items available.");
        } else {
            displayItemDetails(filteredItems.get(0)); // Show details of the first item in the filtered list
        }
    }
}
