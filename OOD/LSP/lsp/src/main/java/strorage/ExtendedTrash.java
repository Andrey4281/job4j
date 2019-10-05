package strorage;

public class ExtendedTrash extends StorageDecorator {
    public ExtendedTrash(Trash trash) {
        super(trash);
    }

    @Override
    public boolean acceptable(Food food) {
        return super.acceptable(food) && !food.getCanReproduct();
    }
}
