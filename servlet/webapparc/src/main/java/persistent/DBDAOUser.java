package persistent;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public final class DBDAOUser implements DAOUser {
    private static final Logger LOG = LogManager.getLogger(DBDAOUser.class);
    private static final DBDAOUser INSTANCE = new DBDAOUser();
    private final DBPool config = DBPoolImpl.getInstance();
    private final DAORole roles = DBDAORole.getInstance();

    private DBDAOUser() {
    }

    public static DBDAOUser getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(User user) {
        try (Connection connection = config.getConnection();
             PreparedStatement st = connection.prepareStatement("INSERT INTO users(name, login, email, photoid, password, role_id) VALUES(?, ?, ?, ?, ?, ?);")) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setString(4, user.getPhotoId());
            st.setString(5, user.getPassword());
            st.setInt(6, roles.findRoleByName(user.getRole().getRoleName()).getId());
            st.executeUpdate();
            LOG.info(String.format("User %s was added to datebase", user.getName()));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public User update(User user) {
        User res = null;
        try (Connection connection = config.getConnection();
        PreparedStatement st = connection.prepareStatement("UPDATE users SET name=?, login=?, email=?, password=?, role_id=? WHERE id=?;")) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setString(4, user.getPassword());
            st.setInt(5, roles.findRoleByName(user.getRole().getRoleName()).getId());
            st.setInt(6, user.getId());
            if (st.executeUpdate() >= 1) {
                LOG.info(String.format("User %s was updated", user.getName()));
                res = user;
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return res;
    }

    @Override
    public User delete(User user) {
        User res = null;
        try (Connection connection = config.getConnection();
        PreparedStatement st = connection.prepareStatement("DELETE FROM users WHERE id=?;")) {
            st.setInt(1, user.getId());
            if (st.executeUpdate() >= 1) {
                LOG.info(String.format("User %d was deleted", user.getId()));
                res = user;
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return res;
    }

    @Override
    public List<User> findAll() {
        List<User> list = new LinkedList<>();
        try (Connection connection = config.getConnection();
             ResultSet set = connection.prepareStatement("SELECT * FROM users;").executeQuery()) {
            while (set.next()) {
                User user = new User(set.getInt("id"), roles.findRoleById(set.getInt("role_id")), set.getString("name"),
                        set.getString("login"), set.getString("email"), set.getDate("createdate"),
                        set.getString("photoid"));
                user.setPassword("password");
                list.add(user);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public User findById(int id) {
        User user = null;
        try (Connection connection = config.getConnection();
        PreparedStatement st = connection.prepareStatement("SELECT * FROM users WHERE id=?;")) {
            st.setInt(1, id);
            try (ResultSet set = st.executeQuery()) {
                if (set.next()) {
                    user = new User(set.getInt("id"), roles.findRoleById(set.getInt("role_id")), set.getString("name"),
                            set.getString("login"), set.getString("email"), set.getDate("createdate"),
                            set.getString("photoid"));
                    user.setPassword(set.getString("password"));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return user;
    }

    @Override
    public User findUserByLogin(String login) {
        User user = null;
        try (Connection connection = config.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT * FROM users WHERE login=?;")) {
            st.setString(1, login);
            try (ResultSet set = st.executeQuery()) {
                if (set.next()) {
                    user = new User(set.getInt("id"), roles.findRoleById(set.getInt("role_id")), set.getString("name"),
                            set.getString("login"), set.getString("email"), set.getDate("createdate"),
                            set.getString("photoid"));
                    user.setPassword(set.getString("password"));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return user;
    }

}
