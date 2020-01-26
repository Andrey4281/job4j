package service;

import models.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StorageServiceTest {

    @Test
    public void whenAddUserShouldGetIt() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-test-context.xml");
        StorageService storageService = context.getBean("storageService", StorageService.class);
        User userOne = context.getBean("userOne", User.class);
        User userTwo = context.getBean("userTwo", User.class);

        storageService.add(userOne);
        storageService.add(userTwo);
        List<User> actual = storageService.findAll();

        assertThat(actual).containsOnly(userOne, userTwo);
    }

}
