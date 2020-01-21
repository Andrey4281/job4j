package storage;

import models.User;
import org.springframework.stereotype.Repository;

@Repository("storage")
public class UserStorageImpl implements UserStorage {
    @Override
    public void add(User user) {
        System.out.println("User was added to storage");
    }
}
