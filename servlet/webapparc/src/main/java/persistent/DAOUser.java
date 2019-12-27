package persistent;

import model.Role;
import model.User;
import java.util.List;

public interface DAOUser {
    void add(User user);
    User update(User user);
    User delete(User user);
    List<User> findAll();
    User findById(int id);
    User findUserByLogin(String login);
}
