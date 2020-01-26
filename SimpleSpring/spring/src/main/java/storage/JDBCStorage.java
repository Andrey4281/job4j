package storage;

import models.User;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Repository("jdbcStorage")
public class JDBCStorage implements Storage {
    private final BasicDataSource source;

    @Autowired
    public JDBCStorage(BasicDataSource source) {
        this.source = source;
    }

    @Override
    public void add(User user) {
        try (Connection connection = source.getConnection();
             PreparedStatement st = connection.prepareStatement("INSERT INTO users(name) VALUES(?);")) {
            st.setString(1, user.getName());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> findAll() {
        List<User> list = new LinkedList<>();
        try (Connection connection = source.getConnection();
             ResultSet set = connection.prepareStatement("SELECT * FROM users;").executeQuery()) {
            while (set.next()) {
                User user = new User(set.getInt("id"), set.getString("name"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
