import models.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.StorageService;

public class ImportUser {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-storage-context.xml");
        StorageService storageService = context.getBean("storageService", StorageService.class);
        storageService.add(context.getBean("userOne", User.class));
        storageService.add(context.getBean("userTwo", User.class));

        System.out.println(storageService.findAll());

    }
}
