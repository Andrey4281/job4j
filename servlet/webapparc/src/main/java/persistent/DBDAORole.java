package persistent;

import model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DBDAORole implements DAORole {
    //private static final Logger LOG = LogManager.getLogger(DBDAORole.class);
    private final DBConfig config = DBConfigPostgreSQL.getInstance();
    private static final DBDAORole INSTANCE = new DBDAORole();

    private DBDAORole() {
    }

    public static DBDAORole getInstance() {
        return INSTANCE;
    }

    @Override
    public Role findRoleById(int id) {
        Role role = null;
        try (Connection connection = config.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT * FROM roles WHERE id=?;")) {
            st.setInt(1, id);
            try (ResultSet set = st.executeQuery()) {
                if (set.next()) {
                    role = new Role(set.getInt("id"), set.getString("rolename"));
                }
            }
        } catch (SQLException e) {
            //LOG.error(e.getMessage(), e);
        }
        return role;
    }

    @Override
    public Role findRoleByName(String roleName) {
        Role role = null;
        try (Connection connection = config.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT * FROM roles WHERE rolename=?;")) {
            st.setString(1, roleName);
            try (ResultSet set = st.executeQuery()) {
                if (set.next()) {
                    role = new Role(set.getInt("id"), set.getString("rolename"));
                }
            }
        } catch (SQLException e) {
            //LOG.error(e.getMessage(), e);
        }
        return role;
    }

    @Override
    public List<Role> findAllRoles() {
        List<Role> list = new LinkedList<>();
        try (Connection connection = config.getConnection();
             ResultSet set = connection.prepareStatement("SELECT * FROM roles;").executeQuery()) {
            while (set.next()) {
                list.add(new Role(set.getInt("id"), set.getString("rolename")));
            }
        } catch (SQLException e) {
            //LOG.error(e.getMessage(), e);
        }
        return list;
    }
}
