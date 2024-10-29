import javax.swing.*;
import java.awt.*;

public class DetailsPanel extends JPanel {
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
        detailsArea.setText(details); // set text with item details
    }
}
