package tracker.sql;

import org.junit.Test;
import tracker.Item;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TrackerSQLTest {
    @Test
    public void checkConnection() {
        try (TrackerSQL sql = new TrackerSQL("app.properties")) {
            assertThat(sql.init(), is(true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}