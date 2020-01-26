package storage;

import models.User;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository("memoryStorage")
public class MemoryStorage implements Storage {
    private final List<User> list = new LinkedList<>();

    @Override
    public void add(User user) {
        list.add(user);
    }

    @Override
    public List<User> findAll() {
        return new LinkedList<>(list);
    }
}
