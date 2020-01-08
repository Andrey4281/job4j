package persistent;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConfigPostgreSQL extends AbstractDBConfig {
    private static final DBConfig INSTANCE = new DBConfigPostgreSQL();

    public static DBConfig getInstance() {
        return INSTANCE;
    }

    private DBConfigPostgreSQL() {
        source.setDriverClassName("org.postgresql.Driver");
        source.setUrl("jdbc:postgresql://127.0.0.1:5432/job4jservlet");
        source.setUsername("postgres");
        source.setPassword("12345");
        source.setMinIdle(5);
        source.setMaxIdle(10);
        source.setMaxOpenPreparedStatements(100);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return source.getConnection();
    }
}
