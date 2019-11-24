package logic;

import model.User;

import java.util.List;

public interface Validate {
    boolean add(User user);
    boolean update(User user);
    boolean delete(User user);
    User findById(int id);
    List<User> findAll();
}
