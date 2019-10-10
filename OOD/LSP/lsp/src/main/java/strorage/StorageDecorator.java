package strorage;

import java.util.List;

public abstract class StorageDecorator implements Storage {
    private final Storage storage;

    protected StorageDecorator(Storage storage) {
        this.storage = storage;
    }

    @Override
    public boolean acceptable(Food food) {
        return storage.acceptable(food);
    }

    @Override
    public void add(Food food) {
        storage.add(food);
    }

    @Override
    public List<Food> getAllProducts() {
        return storage.getAllProducts();
    }

    @Override
    public List<Food> removeAllProducts() {
        return storage.removeAllProducts();
    }
}
