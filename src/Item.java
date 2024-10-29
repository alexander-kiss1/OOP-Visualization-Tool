// fields to hold the items
public class Item {
    private String countryName;
    private String countryCode;
    private String dataName;
    private double value2015;
    private double value2016;
    private double value2017;
    private double value2018;
    private double value2019;
    private double value2020;

    // sets the values
    public Item(String countryName, String countryCode, String dataName, double value2015, double value2016, double value2017, double value2018, double value2019, double value2020) {
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.dataName = dataName;
        this.value2015 = value2015;
        this.value2016 = value2016;
        this.value2017 = value2017;
        this.value2018 = value2018;
        this.value2019 = value2019;
        this.value2020 = value2020;
    }

    // getters for my data
    public String getCountryName() {
        return countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getDataName() {
        return dataName;
    }

    public double getValue2015() {
        return value2015;
    }

    public double getValue2016() {
        return value2016;
    }

    public double getValue2017() {
        return value2017;
    }

    public double getValue2018() {
        return value2018;
    }

    public double getValue2019() {
        return value2019;
    }

    public double getValue2020() {
        return value2020;
    }
}
