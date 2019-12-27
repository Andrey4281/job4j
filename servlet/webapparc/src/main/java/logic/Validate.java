package logic;

import model.Role;
import model.User;

import java.util.List;

public interface Validate {
    boolean add(User user);
    boolean update(User user);
    boolean delete(User user);
    User findById(int id);
    List<User> findAll();
    User findUserByLogin(String login);
    List<Role> findAllRoles();
}
