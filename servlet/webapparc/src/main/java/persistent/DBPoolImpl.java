package persistent;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public final class DBPoolImpl implements DBPool {
    private static final DBPool INSTANCE = new DBPoolImpl();
    private static final Logger LOG = LogManager.getLogger(DBPoolImpl.class);

    public static DBPool getInstance() {
        return INSTANCE;
    }

    private DBPoolImpl() {
    }

    @Override
    public Connection getConnection() {
        Context ctx;
        Connection c = null;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/webapparc");
            c = ds.getConnection();
        } catch (NamingException e) {
            LOG.error(e.getMessage(), e);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return c;
    }
}
