package persistent;

import org.apache.commons.dbcp2.BasicDataSource;

public abstract class AbstractDBConfig implements DBConfig {
    protected final BasicDataSource source = new BasicDataSource();
}
