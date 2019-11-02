package userstorage;

import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorageImpl implements UserStorage {
    private final Map<Integer, User> storage = new HashMap<>();

    @Override
    public boolean add(User user) {
        boolean res = false;
        synchronized (storage) {
            if (storage.get(user.getId()) == null) {
                res = true;
                storage.put(user.getId(), user);
            }
        }
        return res;
    }

    @Override
    public boolean update(User user) {
        boolean res = false;
        synchronized (storage) {
            if (storage.get(user.getId()) != null) {
                res = true;
                storage.replace(user.getId(), user);
            }
        }
        return res;
    }

    @Override
    public boolean delete(User user) {
        boolean res = false;
        synchronized (storage) {
            if (storage.get(user.getId()) != null) {
                res = true;
                storage.remove(user.getId());
            }
        }
        return res;
    }

    @Override
    public boolean transfer(int fromId, int toId, int amount) {
        boolean res = false;
        synchronized (storage) {
            if (storage.get(fromId) != null && storage.get(toId) != null
            && storage.get(fromId).getAmount() >= amount) {
                res = true;
                User from = storage.get(fromId);
                User to = storage.get(toId);
                from.setAmount(from.getAmount() - amount);
                to.setAmount(to.getAmount() + amount);
            }
        }
        return res;
    }
}
