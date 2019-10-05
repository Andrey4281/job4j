package strorage;

import java.util.List;

public class Shop implements Storage {
    private final DAOForFood strorage;
    private int id;

    public Shop(DAOForFood strorage) {
        this.strorage = strorage;
    }

    @Override
    public boolean acceptable(Food food) {
        double value = food.getPercentOfExpaireDate();
        return (value >= 25 && value < 100);
    }

    @Override
    public void add(Food food) {
        setDiscountIfNeeded(food);
        strorage.add(food);
    }

    @Override
    public List<Food> getAllProducts() {
        return strorage.getAllProducts();
    }

    private void setDiscountIfNeeded(Food food) {
        double value = food.getPercentOfExpaireDate();
        if (value >= 75) food.setDisscount(true);
    }


}
