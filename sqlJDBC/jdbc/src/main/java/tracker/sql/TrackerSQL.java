package tracker.sql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tracker.ITracker;
import tracker.Item;

import java.io.InputStream;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class TrackerSQL implements ITracker, AutoCloseable {
    private static final Logger LOG = LogManager.getLogger(TrackerSQL.class.getName());
    private Connection connection;
    private String config;

    public TrackerSQL(final String config) {
        this.config = config;
    }

    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream(config)) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            try (Statement statement = connection.createStatement()) {
                statement.execute("CREATE TABLE IF NOT EXISTS items (id SERIAL PRIMARY KEY,name VARCHAR(200));");
            }
        } catch (Exception e) {
            LOG.error("Init error", e);
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement st =
                     connection.prepareStatement("INSERT INTO items(name) VALUES(?);")) {
            st.setString(1, item.getName());
            st.execute();
        } catch (SQLException e) {
            LOG.error("Invalid add operation", e);
        }
        return item;
    }

    @Override
    public void replace(String id, Item item) {
        try (PreparedStatement st = connection.prepareStatement("UPDATE items SET name=? WHERE id=?;")) {
            st.setString(1, item.getName());
            st.setInt(2, Integer.parseInt(id));
            st.execute();
        } catch (SQLException e) {
            LOG.error("Invalid update operation", e);
        }
    }

    @Override
    public void delete(String id) {
        try (PreparedStatement st = connection.prepareStatement("DELETE FROM items WHERE id=?;")) {
            st.setInt(1, Integer.parseInt(id));
            st.execute();
        } catch (SQLException e) {
            LOG.error("Invalid delete operation", e);
        }

    }

    @Override
    public Item[] findAll() {
        Item[] items = new Item[0];
        try (Statement st = connection.createStatement(); ResultSet set =
                st.executeQuery("SELECT * FROM items;")) {
            items = handleResultSet(set);
        } catch (SQLException e) {
            LOG.error("Invalid findAll operation", e);
        }
        return items;
    }

    @Override
    public Item[] findByName(String key) {
        Item[] items = new Item[0];
        try (PreparedStatement st = connection.prepareStatement("SELECT * FROM items WHERE name=?;")) {
            st.setString(1, key);
            try (ResultSet set = st.executeQuery()) {
                items = handleResultSet(set);
            }
        } catch (SQLException e) {
            LOG.error("Invalid findByName operation", e);
        }
        return items;
    }

    @Override
    public Item findById(String id) {
        Item item = null;
        try (PreparedStatement st = connection.prepareStatement("SELECT * FROM items WHERE id=?;")) {
            st.setInt(1, Integer.parseInt(id));
            try (ResultSet set = st.executeQuery()) {
                if (set.next()) {
                    item = new Item(set.getString("id"), set.getString("name"));
                }
            }
        } catch (SQLException e) {
            LOG.error("Invalid findById operation", e);
        }
        return item;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    private Item[] handleResultSet(ResultSet set) throws SQLException {
        List<Item> itemList = new LinkedList<>();
        while (set.next()) {
            itemList.add(new Item(set.getString("id"), set.getString("name")));
        }
        return itemList.stream().toArray(Item[]::new);
    }
}
