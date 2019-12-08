package persistent;

import model.User;
import java.util.List;

public interface Store {
    void add(User user);
    User update(User user);
    User delete(User user);
    List<User> findAll();
    User findById(int id);
}
