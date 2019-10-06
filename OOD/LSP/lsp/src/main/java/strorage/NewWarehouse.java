package strorage;

public class NewWarehouse extends StorageDecorator {

    public NewWarehouse(Warehouse warehouse) {
        super(warehouse);
    }

    @Override
    public boolean acceptable(Food food) {
        return super.acceptable(food) && !food.getneededMinusTemperature();
    }
}
