package logic;

import model.User;
import persistent.Store;
import persistent.StoreMemory;

import java.util.List;

public class ValidateImpl implements Validate {
    private static final Validate INSTANCE = new ValidateImpl();
    private final Store store = StoreMemory.INSTANCE;

    private ValidateImpl() {

    }

    public static Validate getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(User user) {
        store.add(user);
        return true;
    }

    @Override
    public boolean update(User user) {
        User res = store.update(user);
        boolean flag = true;
        if (res == null) {
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean delete(User user) {
        User res = store.delete(user);
        boolean flag = true;
        if (res == null) {
            flag = false;
        }
        return flag;
    }

    @Override
    public User findById(int id) {
        return store.findById(id);
    }

    @Override
    public List<User> findAll() {
        return store.findAll();
    }
}
