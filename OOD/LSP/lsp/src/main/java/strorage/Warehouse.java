package strorage;

import java.util.List;

public class Warehouse implements Storage {
    private final DAOForFood strorage;
    private int id;

    public Warehouse(DAOForFood strorage) {
        this.strorage = strorage;
    }

    @Override
    public boolean acceptable(Food food) {
        return food.getPercentOfExpaireDate() < 25;
    }

    @Override
    public void add(Food food) {
        strorage.add(food);
    }

    @Override
    public List<Food> getAllProducts() {
        return strorage.getAllProducts();
    }

    @Override
    public List<Food> removeAllProducts() {
        return strorage.removeAllProducts();
    }


}
