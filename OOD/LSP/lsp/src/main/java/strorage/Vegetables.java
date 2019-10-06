package strorage;

import java.util.Calendar;

public class Vegetables extends Food {
    public Vegetables(String name, Calendar expaireDate, Calendar createDate, double price, boolean disscount, boolean canReproduct) {
        super(name, expaireDate, createDate, price, disscount, canReproduct, true);
    }
}
