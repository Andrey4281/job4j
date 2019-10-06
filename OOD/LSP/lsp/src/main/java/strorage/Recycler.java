package strorage;

public class Recycler extends StorageDecorator {
    public Recycler(Trash trash) {
        super(trash);
    }

    @Override
    public boolean acceptable(Food food) {
        return super.acceptable(food) && food.getCanReproduct();
    }
}
