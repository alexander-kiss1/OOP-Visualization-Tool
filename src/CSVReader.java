import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    // Method to read data from a CSV file and return a list of Item objects
    public List<Item> readCSV(String filePath) {
        List<Item> items = new ArrayList<>(); // List to store the items read from the CSV file
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line; // Variable to hold each line read from the file

            // Skip the header line of the CSV file
            br.readLine();

            // Read lines from the CSV until there are no more lines
            while ((line = br.readLine()) != null) {
                // Split the line into values using a comma
                String[] values = line.split(",");

                // Ensure the values array has enough elements to avoid errors
                if (values.length >= 10) {
                    String countryName = values[0]; // First column: Country Name
                    String countryCode = values[1]; // Second column: Country Code
                    String dataName = values[2]; // Third column: Data Name

                    // Parse the values for each year from the CSV and convert to double
                    double value2015 = Double.parseDouble(values[4]);
                    double value2016 = Double.parseDouble(values[5]);
                    double value2017 = Double.parseDouble(values[6]);
                    double value2018 = Double.parseDouble(values[7]);
                    double value2019 = Double.parseDouble(values[8]);
                    double value2020 = Double.parseDouble(values[9]);

                    // Create a new Item object with the read data
                    Item item = new Item(countryName, countryCode, dataName,
                            value2015, value2016, value2017,
                            value2018, value2019, value2020);
                    // Add the created item to the list
                    items.add(item);
                } else {
                    // If the line does not have enough data, skip it and print an error message
                    System.out.println("Skipping line due to insufficient data: " + line);
                }
            }
        } catch (IOException e) {
            // Print the stack trace if an error occurs with the file
            e.printStackTrace();
        }
        // Return the list of items read from the CSV file
        return items;
    }
}

