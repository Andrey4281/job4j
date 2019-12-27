package persistent;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBPool {
    Connection getConnection();
}
