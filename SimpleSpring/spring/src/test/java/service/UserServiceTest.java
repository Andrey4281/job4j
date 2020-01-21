package service;

import configuration.SimpleSpringConfiguration;
import models.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class UserServiceTest {

    @Test
    public void whenConfigureByXMLOrAnnotationsShouldGetResut() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        UserService service = (UserService) context.getBean("service");
        service.add(new User());
    }

    @Test
    public void whenConfigureByJavaClassShouldGetResult() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SimpleSpringConfiguration.class);
        UserService service = context.getBean("service", UserService.class);
        service.add(new User());


    }

}
