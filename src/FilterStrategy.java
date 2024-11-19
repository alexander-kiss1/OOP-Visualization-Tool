import java.util.List;

public interface FilterStrategy {
    List<Item> applyFilter(List<Item> items);
}
