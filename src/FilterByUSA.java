import java.util.List;
import java.util.stream.Collectors;

public class FilterByUSA implements FilterStrategy {
    @Override
    public List<Item> applyFilter(List<Item> items) {
        return items.stream()
                .filter(item -> "United States".equals(item.getCountryName()))
                .collect(Collectors.toList());
    }
}
