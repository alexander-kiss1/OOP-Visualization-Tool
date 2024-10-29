public class Item {
    private String name;
    private double value;
    private int count;

    public Item(String name, double value, int count) {
        this.name = name;
        this.value = value;
        this.count = count;
    }

    // Getters
    public String getName() { return name; }
    public double getValue() { return value; }
    public int getCount() { return count; }
}
