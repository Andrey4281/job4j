package persistent;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBConfig {
    Connection getConnection() throws SQLException;
}
