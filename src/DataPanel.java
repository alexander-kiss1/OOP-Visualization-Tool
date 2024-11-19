import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataPanel extends JPanel implements Subject {
    private JTable table;
    private List<Item> originalItems; // Store original items
    private List<Item> filteredItems; // Store filtered items
    private DetailsPanel detailsPanel;
    private StatsPanel statsPanel;
    private List<Observer> observers; // List of observers (DetailsPanel, StatsPanel)

    // construct for DataPanel
    public DataPanel(List<Item> items, DetailsPanel detailsPanel, StatsPanel statsPanel) {
        this.originalItems = items;
        this.filteredItems = new ArrayList<>(items); // Initialize with all items
        this.detailsPanel = detailsPanel;
        this.statsPanel = statsPanel;
        this.observers = new ArrayList<>(); // Initialize the list of observers

        setLayout(new BorderLayout()); // Set layout for the DataPanel

        // Create column names for the table
        String[] columnNames = {"Country Name", "Country Code", "Data Name", "2015", "2016", "2017", "2018", "2019", "2020"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        updateTableModel(model, filteredItems);

        // Create the table and enable row selection
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        Item selectedItem = filteredItems.get(selectedRow);
                        detailsPanel.displayItemDetails(selectedItem); // Update the details panel with selected item
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

        // Add action listeners to filter buttons
        filterButton1.addActionListener(e -> applyFilter1(model));  // Filter by USA
        filterButton2.addActionListener(e -> applyFilter2(model));  // Filter by 2019 Growth > 2%
        filterButton3.addActionListener(e -> applyFilter3(model));  // Filter by Negative Growth in 2020
        resetButton.addActionListener(e -> resetFilters(model));    // Reset filters

        // Add buttons to the filter panel
        filterPanel.add(filterButton1);
        filterPanel.add(filterButton2);
        filterPanel.add(filterButton3);
        filterPanel.add(resetButton);

        add(filterPanel, BorderLayout.NORTH); // Add the filter panel to the top of DataPanel
    }

    // Add an observer (DetailsPanel, StatsPanel) to be notified when data changes
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // Remove an observer from the list of observers
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    // Notify all observers that the filtered data has changed
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(filteredItems); // Notify each observer with the filtered data
        }
    }

    // Apply filter 1
    private void applyFilter1(DefaultTableModel model) {
        filteredItems = originalItems.stream()
                .filter(item -> "United States".equals(item.getCountryName()))
                .collect(Collectors.toList());

        updateTableModel(model, filteredItems);
        notifyObservers();
    }

    // Apply filter 2
    private void applyFilter2(DefaultTableModel model) {
        filteredItems = originalItems.stream()
                .filter(item -> item.getValue2019() > 2)
                .collect(Collectors.toList());

        updateTableModel(model, filteredItems);
        notifyObservers();
    }

    // Apply filter 3
    private void applyFilter3(DefaultTableModel model) {
        filteredItems = originalItems.stream()
                .filter(item -> item.getValue2020() < 0)
                .collect(Collectors.toList());

        updateTableModel(model, filteredItems);
        notifyObservers();
    }

    // Reset filters to show all original items
    private void resetFilters(DefaultTableModel model) {
        filteredItems = new ArrayList<>(originalItems); // Reset to the original items
        updateTableModel(model, filteredItems);
        notifyObservers();
    }

    // Update the table model with the filtered list of items
    public void updateTableModel(DefaultTableModel model, List<Item> items) {
        model.setRowCount(0); // Clear existing rows in the table
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
