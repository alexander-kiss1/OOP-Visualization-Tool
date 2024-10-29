import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataPanel extends JPanel {
    private JTable table;
    private List<Item> originalItems; // Store original items
    private List<Item> filteredItems; // Store filtered items
    private DetailsPanel detailsPanel;
    private StatsPanel statsPanel;

    // construct for DataPanel
    public DataPanel(List<Item> items, DetailsPanel detailsPanel, StatsPanel statsPanel) {
        this.originalItems = items;
        this.filteredItems = new ArrayList<>(items); // Start with all items
        this.detailsPanel = detailsPanel; // link to details
        this.statsPanel = statsPanel; // link to stats
        setLayout(new BorderLayout()); // layout purposes

        // creates column names
        String[] columnNames = {"Country Name", "Country Code", "Data Name", "2015", "2016", "2017", "2018", "2019", "2020"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        updateTableModel(model, filteredItems);

        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // updates details when a specific row selected
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        Item selectedItem = filteredItems.get(selectedRow);
                        detailsPanel.displayItemDetails(selectedItem);
                    }
                }
            }
        });

        // sorting for table rows
        table.setAutoCreateRowSorter(true);

        add(new JScrollPane(table), BorderLayout.CENTER);

        // Add filter buttons
        JPanel filterPanel = new JPanel();
        JButton filterButton1 = new JButton("Filter by USA");
        JButton filterButton2 = new JButton("Filter by 2019 Growth > 2%");
        JButton filterButton3 = new JButton("Filter by Negative Growth");
        JButton resetButton = new JButton("Reset Filters");

        //action listeners to filter buttons
        filterButton1.addActionListener(e -> applyFilter1(model));
        filterButton2.addActionListener(e -> applyFilter2(model));
        filterButton3.addActionListener(e -> applyFilter3(model));
        resetButton.addActionListener(e -> resetFilters(model));

        //buttons to filter panel
        filterPanel.add(filterButton1);
        filterPanel.add(filterButton2);
        filterPanel.add(filterButton3);
        filterPanel.add(resetButton);

        add(filterPanel, BorderLayout.NORTH);
    }
    // only shows data from USA
    private void applyFilter1(DefaultTableModel model) {
        // Example filter: Show only items from the United States
        filteredItems = originalItems.stream()
                .filter(item -> "United States".equals(item.getCountryName()))
                .collect(Collectors.toList());

        updateTableModel(model, filteredItems);
        statsPanel.displayStats(filteredItems); // Update stats panel
    }
    // Apply to show >2% growth
    private void applyFilter2(DefaultTableModel model) {
        // Example filter: Show items where 2019 value is greater than 2%
        filteredItems = originalItems.stream()
                .filter(item -> item.getValue2019() > 2)
                .collect(Collectors.toList());

        updateTableModel(model, filteredItems);
        statsPanel.displayStats(filteredItems); // Update stats panel
    }
    // item with negative growth for 2020
    private void applyFilter3(DefaultTableModel model) {
        // Example filter: Show items with negative growth in 2020
        filteredItems = originalItems.stream()
                .filter(item -> item.getValue2020() < 0)
                .collect(Collectors.toList());

        updateTableModel(model, filteredItems);
        statsPanel.displayStats(filteredItems); // Update stats panel
    }

    // resets filters and shows original
    private void resetFilters(DefaultTableModel model) {
        // Reset to original items
        filteredItems = new ArrayList<>(originalItems);
        updateTableModel(model, filteredItems);
        statsPanel.displayStats(filteredItems); // Update stats panel
    }

    // updates the table with items
    public void updateTableModel(DefaultTableModel model, List<Item> items) {
        model.setRowCount(0); // Clear existing rows
        for (Item item : items) {
            model.addRow(new Object[]{
                    item.getCountryName(),
                    item.getCountryCode(),
                    item.getDataName(),
                    item.getValue2015(),
                    item.getValue2016(),
                    item.getValue2017(),
                    item.getValue2018(),
                    item.getValue2019(),
                    item.getValue2020()
            });
        }
    }
}
