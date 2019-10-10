package strorage;

import java.util.List;

public interface DAOForFood {
    void add(Food food);
    List<Food> getAllProducts();
    List<Food> removeAllProducts();
}
