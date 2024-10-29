import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;

public class DataPanel extends JPanel {
    private JTable table;
    private List<Item> items;

    public DataPanel(List<Item> items) {
        this.items = items;
        setLayout(new BorderLayout());

        String[] columnNames = {"Name", "Value", "Count"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        updateTableModel(model, items);

        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                // Update details panel based on selected row
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    Item selectedItem = items.get(selectedRow);
                    // Update details panel here...
                }
            }
        });

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void updateTableModel(DefaultTableModel model, List<Item> items) {
        model.setRowCount(0);
        for (Item item : items) {
            model.addRow(new Object[]{item.getName(), item.getValue(), item.getCount()});
        }
    }
}
