package service;


import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import storage.Storage;

import java.util.List;

@Service("storageService")
public class StorageService {
    private final Storage storage;

    @Autowired
    public StorageService(@Qualifier("jdbcStorage") Storage storage) {
        this.storage = storage;
    }

    public void add(User user) {
        storage.add(user);
    }

    public List<User> findAll() {
        return storage.findAll();
    }
}
