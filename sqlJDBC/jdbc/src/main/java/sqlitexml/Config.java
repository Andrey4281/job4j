package sqlitexml;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    private final Properties values = new Properties();
    private String propertiesName;

    public Config(final String propertiesName) {
        this.propertiesName = propertiesName;
    }

    public void init() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream(propertiesName)) {
            values.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public String get(String key) {
        return this.values.getProperty(key);
    }
}