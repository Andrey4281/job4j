package tracker.sql;

import org.junit.Test;
import tracker.Item;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TrackerSQLTest {

    private Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void createItem() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("name", "desc"));
            assertThat(tracker.findByName("name").length, is(1));
        }
    }

    @Test
    public void updateItem() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item item = tracker.add(new Item("name", "desc"));
            tracker.replace(item.getId(), new Item("name1", "desc1"));
            item = tracker.findById(item.getId());
            assertThat(item.getName(), is("name1"));
            assertThat(item.getDescription(), is("desc1"));
        }
    }


}