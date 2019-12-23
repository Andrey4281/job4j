package service;

import model.User;

@FunctionalInterface
public interface AddUserInterface {
    void addField(User user, String param);
}
