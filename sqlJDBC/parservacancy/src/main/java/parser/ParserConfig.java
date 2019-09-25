package parser;

import sqlitexml.Config;

import java.io.InputStream;
import java.util.Properties;

/**
 * Class for saving information from file app.properties
 * @author asemenov
 * @since 25.09.2019
 * @version 1
 */
public class ParserConfig {
    private final String propertiesName;
    private final Properties values = new Properties();

    public ParserConfig(final String fileName) {
        this.propertiesName = fileName;
    }

    public void init() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream(propertiesName)) {
            values.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public String getKey(String key) {
        return values.getProperty(key);
    }
}
