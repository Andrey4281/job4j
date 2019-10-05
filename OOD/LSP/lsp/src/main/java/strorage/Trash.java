package strorage;

import java.util.List;

public class Trash implements Storage {
    private final DAOForFood strorage;
    private int id;

    public Trash(DAOForFood strorage) {
        this.strorage = strorage;
    }

    @Override
    public boolean acceptable(Food food) {
        return food.getPercentOfExpaireDate() >= 100;
    }

    @Override
    public void add(Food food) {
        strorage.add(food);
    }

    @Override
    public List<Food> getAllProducts() {
        return strorage.getAllProducts();
    }
}
