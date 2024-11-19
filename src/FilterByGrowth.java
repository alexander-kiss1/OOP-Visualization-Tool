import java.util.List;
import java.util.stream.Collectors;


public class FilterByGrowth implements FilterStrategy {
    private double threshold;

    public FilterByGrowth(double threshold) {
        this.threshold = threshold;
    }

    @Override
    public List<Item> applyFilter(List<Item> items) {
        return items.stream()
                .filter(item -> item.getValue2019() > threshold)
                .collect(Collectors.toList());
    }
}
