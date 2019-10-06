package strorage;

public class WarehouseWithMinusTemperature extends StorageDecorator {
    public WarehouseWithMinusTemperature(Warehouse warehouse) {
        super(warehouse);
    }

    @Override
    public boolean acceptable(Food food) {
        return super.acceptable(food) && food.getneededMinusTemperature();
    }
}
