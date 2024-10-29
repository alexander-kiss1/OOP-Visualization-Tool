import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StatsPanel extends JPanel {
    private JLabel statsLabel;

    public StatsPanel(List<Item> items) {
        setLayout(new BorderLayout());
        statsLabel = new JLabel();
        add(statsLabel, BorderLayout.CENTER);
        updateStats(items);
    }

    private void updateStats(List<Item> items) {
        if (items.isEmpty()) {
            statsLabel.setText("No data available.");
            return;
        }

        double sum = 0;
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;

        for (Item item : items) {
            double value = item.getValue();
            sum += value;
            if (value < min) min = value;
            if (value > max) max = value;
        }

        double mean = sum / items.size();
        double variance = 0;

        for (Item item : items) {
            variance += Math.pow(item.getValue() - mean, 2);
        }
        variance /= items.size();
        double stdDev = Math.sqrt(variance);

        String statsText = String.format("Mean: %.2f, Min: %.2f, Max: %.2f, StdDev: %.2f",
                mean, min, max, stdDev);
        statsLabel.setText(statsText);
    }
}
