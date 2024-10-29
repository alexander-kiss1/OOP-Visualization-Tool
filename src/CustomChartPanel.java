import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; // This is the JFreeChart class
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CustomChartPanel extends JPanel {
    public CustomChartPanel(List<Item> items) {
        setLayout(new BorderLayout());
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Item item : items) {
            dataset.addValue(item.getValue2015(), "2015", item.getCountryName());
            dataset.addValue(item.getValue2016(), "2016", item.getCountryName());
            dataset.addValue(item.getValue2017(), "2017", item.getCountryName());
            dataset.addValue(item.getValue2018(), "2018", item.getCountryName());
            dataset.addValue(item.getValue2019(), "2019", item.getCountryName());
            dataset.addValue(item.getValue2020(), "2020", item.getCountryName());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Country Data by Year",       // Chart title
                "Country",                     // X-axis label
                "Value in %",                       // Y-axis label
                dataset,                       // Dataset
                PlotOrientation.VERTICAL,      // Plot orientation
                true,                          // Include legend
                true,                          // Tooltips
                false                          // URLs
        );

        ChartPanel jFreeChartPanel = new ChartPanel(chart); // JFreeChart's ChartPanel
        add(jFreeChartPanel, BorderLayout.CENTER);
    }
}
