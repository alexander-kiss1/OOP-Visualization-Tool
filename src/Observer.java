import java.util.List;

public interface Observer {
    void update(List<Item> filteredItems);
}
