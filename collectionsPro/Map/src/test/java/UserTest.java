import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void showMap() {
        User userFirst = new User("Andrey", 0,
                new GregorianCalendar(1989, 9, 25));
        User userSecond = new User("Andrey", 0,
                new GregorianCalendar(1989, 9, 25));

        Map<User, Object> map = new HashMap<>();
        map.put(userFirst, "first");
        map.put(userSecond, "second");

        System.out.println(map);

    }

}