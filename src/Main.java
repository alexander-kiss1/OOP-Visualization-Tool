// Alex Kiss

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Data Visualization");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            CVSReader csvReader = new CVSReader();
            List<Item> items = csvReader.readCSV("data.csv");

            DataPanel dataPanel = new DataPanel(items);
            StatsPanel statsPanel = new StatsPanel(items);
            ChartPanel chartPanel = new ChartPanel(items);
            DetailsPanel detailsPanel = new DetailsPanel();

            frame.add(dataPanel, BorderLayout.CENTER);
            frame.add(statsPanel, BorderLayout.EAST);
            frame.add(chartPanel, BorderLayout.SOUTH);
            frame.add(detailsPanel, BorderLayout.NORTH);

            frame.pack();
            frame.setVisible(true);
        });
    }
}
