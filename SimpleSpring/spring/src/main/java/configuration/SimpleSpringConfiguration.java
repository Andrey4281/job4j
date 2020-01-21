package configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.UserService;
import storage.UserStorage;
import storage.UserStorageImpl;

@Configuration
public class SimpleSpringConfiguration {
    @Bean
    public UserStorage storage() {
        return new UserStorageImpl();
    }

    @Bean
    public UserService service() {
        return new UserService(storage());
    }
}
