package strorage;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class DAOForFoodImplCollect implements DAOForFood {
    private final Collection<Food> storage;

    public DAOForFoodImplCollect(Collection<Food> storage) {
        this.storage = storage;
    }

    @Override
    public void add(Food food) {
        storage.add(food);
    }

    @Override
    public List<Food> getAllProducts() {
        return new LinkedList<>(storage);
    }

    @Override
    public List<Food> removeAllProducts() {
        List result = new LinkedList(storage);
        storage.removeAll(storage);
        return result;
    }
}
