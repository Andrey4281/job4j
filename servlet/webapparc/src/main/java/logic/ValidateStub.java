package logic;

import model.Role;
import model.User;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ValidateStub implements Validate {
    private final Map<Integer, User> store = new HashMap<>();
    private int id = 0;

    @Override
    public boolean add(User user) {
        user.setId(id++);
        store.put(user.getId(), user);
        return true;
    }

    @Override
    public boolean update(User user) {
        return store.replace(user.getId(), user) != null ? true : false;
    }

    @Override
    public boolean delete(User user) {
        return store.remove(user.getId()) != null ? true : false;
    }

    @Override
    public User findById(int id) {
        return store.get(id);
    }

    @Override
    public List<User> findAll() {
        return new LinkedList<>(this.store.values());
    }

    @Override
    public User findUserByLogin(String login) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Role> findAllRoles() {
        throw new UnsupportedOperationException();
    }
}
