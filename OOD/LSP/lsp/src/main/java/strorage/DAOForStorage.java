package strorage;

import java.util.List;

public interface DAOForStorage {
    void add(Storage storage);
    List<Storage> loadAllStorages();
}
