package storage;

import models.User;

import java.util.List;

public interface Storage {
    void add(User user);
    List<User> findAll();
}
