import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CVSReader {
    public List<Item> readCSV(String filePath) {
        List<Item> items = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                items.add(new Item(values[0], Double.parseDouble(values[1]), Integer.parseInt(values[2])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }
}


