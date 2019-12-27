package persistent;

import model.User;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public enum DAOUserMemory implements DAOUser {
    INSTANCE;
    private final AtomicInteger idForUsers = new AtomicInteger(0);
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();

    @Override
    public void add(User user) {
        user.setId(idForUsers.incrementAndGet());
        users.put(user.getId(), user);
    }

    @Override
    public User update(User user) {
        return users.computeIfPresent(user.getId(), (k, v)-> {
            v.setName(user.getName());
            v.setLogin(user.getLogin());
            v.setEmail(user.getEmail());
            v.setCreateDate(user.getCreateDate());
            return v;
        });
    }

    @Override
    public User delete(User user) {
        return users.remove(user.getId());
    }

    @Override
    public List<User> findAll() {
        return new LinkedList<>(users.values());
    }

    @Override
    public User findById(int id) {
        return users.get(id);
    }

    @Override
    public User findUserByLogin(String login) {
        throw new UnsupportedOperationException();
    }
}
