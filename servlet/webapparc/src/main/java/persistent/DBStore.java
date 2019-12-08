package persistent;

import model.User;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DBStore implements Store {
    private static final Logger LOG = LogManager.getLogger(DBStore.class);
    private final BasicDataSource source = new BasicDataSource();
    private static final DBStore INSTANCE = new DBStore();

    private DBStore() {
        source.setDriverClassName("org.postgresql.Driver");
        source.setUrl("jdbc:postgresql://127.0.0.1:5432/job4jservlet");
        source.setUsername("postgres");
        source.setPassword("12345");
        source.setMinIdle(5);
        source.setMaxIdle(10);
        source.setMaxOpenPreparedStatements(100);
    }

    public static DBStore getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(User user) {
        try (Connection connection = source.getConnection();
             PreparedStatement st = connection.prepareStatement("INSERT INTO users(name, login, email) VALUES(?, ?, ?);")) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.executeUpdate();
            LOG.info(String.format("User %s was added to datebase", user.getName()));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public User update(User user) {
        User res = null;
        try (Connection connection = source.getConnection();
        PreparedStatement st = connection.prepareStatement("UPDATE users SET name=?,login=?,email=? WHERE id=?;")) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setInt(4, user.getId());
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
        try (Connection connection = source.getConnection();
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
        try (Connection connection = source.getConnection();
             ResultSet set = connection.prepareStatement("SELECT * FROM users;").executeQuery()) {
            while (set.next()) {
                list.add(new User(set.getInt("id"), set.getString("name"),
                        set.getString("login"), set.getString("email"), set.getDate("createdate")));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public User findById(int id) {
        User user = null;
        try (Connection connection = source.getConnection();
        PreparedStatement st = connection.prepareStatement("SELECT * FROM users WHERE id=?;")) {
            st.setInt(1, id);
            try (ResultSet set = st.executeQuery()) {
                if (set.next()) {
                    user = new User(set.getInt("id"), set.getString("name"),
                            set.getString("login"), set.getString("email"), set.getDate("createdate"));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return user;
    }
}
