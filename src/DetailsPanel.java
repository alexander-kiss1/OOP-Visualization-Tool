import javax.swing.*;
import java.awt.*;

public class DetailsPanel extends JPanel {
    private JTextArea detailsArea;

    public DetailsPanel() {
        setLayout(new BorderLayout());
        detailsArea = new JTextArea();
        add(new JScrollPane(detailsArea), BorderLayout.CENTER);
    }

    public void updateDetails(Item item) {
        detailsArea.setText("Name: " + item.getName() + "\nValue: " + item.getValue() + "\nCount: " + item.getCount());
    }
}
