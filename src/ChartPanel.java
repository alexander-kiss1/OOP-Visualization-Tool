import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChartPanel extends JPanel {
    public ChartPanel(List<Item> items) {
        setLayout(new BorderLayout());
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Item item : items) {
            dataset.addValue(item.getValue(), "Values", item.getName());
        }

        JFreeChart chart = ChartFactory.createBarChart("Item Values", "Item", "Value", dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel, BorderLayout.CENTER);
    }
}
