package strorage;

import java.util.LinkedList;
import java.util.List;

public class DAOForStorageImplList implements DAOForStorage {
    private final List<Storage> list = new LinkedList<>();

    @Override
    public void add(Storage storage) {
        list.add(storage);
    }

    @Override
    public List<Storage> loadAllStorages() {
        return new LinkedList<>(list);
    }
}
