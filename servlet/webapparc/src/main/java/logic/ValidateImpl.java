package logic;

import model.Role;
import model.User;
import persistent.DAORole;
import persistent.DBDAORole;
import persistent.DBDAOUser;
import persistent.DAOUser;

import java.util.List;

public class ValidateImpl implements Validate {
    private static final Validate INSTANCE = new ValidateImpl();
    private final DAOUser daoUser = DBDAOUser.getInstance();
    private final DAORole daoRole = DBDAORole.getInstance();

    private ValidateImpl() {

    }

    public static Validate getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(User user) {
        daoUser.add(user);
        return true;
    }

    @Override
    public boolean update(User user) {
        User res = daoUser.update(user);
        boolean flag = true;
        if (res == null) {
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean delete(User user) {
        User res = daoUser.delete(user);
        boolean flag = true;
        if (res == null) {
            flag = false;
        }
        return flag;
    }

    @Override
    public User findById(int id) {
        return daoUser.findById(id);
    }

    @Override
    public List<User> findAll() {
        return daoUser.findAll();
    }

    @Override
    public User findUserByLogin(String login) {
        return daoUser.findUserByLogin(login);
    }

    @Override
    public List<Role> findAllRoles() {
        return daoRole.findAllRoles();
    }
}
