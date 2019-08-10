package config;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConfigTest {
    private Config config;

    @Before
    public void setConfig() {
        config = new Config("./../../app.properties");
        config.load();
    }

    @Test
    public void sequentialOfValueMethodInvocation() {
        assertThat(config.getCountOfValues(), is(5));
        assertThat(config.value("hibernate.dialect"),
                is("org.hibernate.dialect.PostgreSQLDialect"));
        assertThat(config.value("hibernate.connection.url"),
                is("jdbc:postgresql://127.0.0.1:5432/trackstudio"));
        assertThat(config.value("hibernate.connection.driver_class"),
                is("org.postgresql.Driver"));
        assertThat(config.value("hibernate.connection.username"),
                is("postgres"));
        assertThat(config.value("hibernate.connection.password"),
                is("password"));
        assertEquals(config.value("Andrey"), null);
    }

}