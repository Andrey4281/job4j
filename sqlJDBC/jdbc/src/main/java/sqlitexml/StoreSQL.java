package sqlitexml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class StoreSQL implements AutoCloseable {
    private static final Logger LOG = LogManager.getLogger(StoreSQL.class.getName());

    private final Config config;
    private Connection connect;

    public StoreSQL(Config config) {
        this.config = config;
    }

    public void init() {
        try {
            Class.forName("org.sqlite.JDBC");
            connect = DriverManager.getConnection(config.get("url"), config.get("username"),
                    config.get("password"));
            LOG.info("Connected to database {}", config.get("url"));
            createTable();
            LOG.info("Creating table entry if it not exists");
        } catch (SQLException e) {
            LOG.error("Error of connection to database", e);
        } catch (ClassNotFoundException e) {
            LOG.error("Driver not found", e);
        }
    }

    public void generate(int size) {
        try {
            connect.setAutoCommit(false);
            deleteAllEntries();
            addNEntries(size);
            connect.commit();
        } catch (SQLException e) {
            try {
                connect.rollback();
            } catch (SQLException e1) {
                LOG.error("Rollback error", e1);
            }
        } finally {
            try {
                connect.setAutoCommit(true);
            } catch (SQLException e) {
                LOG.error("Autocommit error", e);
            }
        }
    }

    public List<Entry> load() {
        List<Entry> res = new LinkedList<>();
        try (Statement st = connect.createStatement();
        ResultSet set = st.executeQuery("SELECT * FROM entry")) {
            while (set.next()) {
                res.add(new Entry(set.getInt("field")));
            }
        } catch (SQLException e) {
            LOG.error("Load error", e);
        }
        return res;
    }

    @Override
    public void close() throws Exception {
        if (connect != null) {
            connect.close();
        }
    }

    private void createTable() {
        try (Statement st = this.connect.createStatement()) {
            st.execute("CREATE TABLE IF NOT EXISTS entry(field integer);");
        } catch (SQLException e) {
            LOG.error("Error creating table entry", e);
        }
    }

    private void deleteAllEntries() {
        try (Statement st = this.connect.createStatement()) {
            st.execute("DELETE FROM entry;");
            LOG.info("All old entries were removed");
        } catch (SQLException e) {
            LOG.error("Error removing entries", e);
        }
    }

    private void addNEntries(int n) {
        try (PreparedStatement st = this.connect.prepareStatement("INSERT INTO entry(field) VALUES(?);")) {
            for (int i = 1; i <= n; i++) {
                st.setInt(1, i);
                st.addBatch();
            }
            st.executeBatch();
            LOG.info("N of entries were added");
        } catch (SQLException e) {
            LOG.error("Error adding entries", e);
        }
    }
}
