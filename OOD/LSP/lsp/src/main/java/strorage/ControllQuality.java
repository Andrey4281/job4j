package strorage;

import java.util.List;

public class ControllQuality {
    private final DAOForStorage daoForStorage;

    public ControllQuality(DAOForStorage daoForStorage) {
        this.daoForStorage = daoForStorage;
    }

    public void distributeFoodInStorage(List<? extends Food> products) {
        List<Storage> storages = daoForStorage.loadAllStorages();
        addProductsToStorages(products, storages);
    }

    protected void addProductsToStorages(List<? extends Food> products, List<Storage> storages) {
        for (Food currentProduct: products) {
            for (Storage currentStorage: storages) {
                if (currentStorage.acceptable(currentProduct)) {
                    currentStorage.add(currentProduct);
                    break;
                }
            }
        }
    }
}
