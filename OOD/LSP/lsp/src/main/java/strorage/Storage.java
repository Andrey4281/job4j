package strorage;

import java.util.List;

public interface Storage {
    boolean acceptable(Food food);
    void add(Food food);
    List<Food> getAllProducts();
}
