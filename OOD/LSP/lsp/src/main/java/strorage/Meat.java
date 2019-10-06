package strorage;

import java.util.Calendar;

public class Meat extends Food {
    private final String kindOfMeat;

    public Meat(String name, Calendar expaireDate, Calendar createDate, double price, boolean disscount, String kindOfMeat) {
        super(name, expaireDate, createDate, price, disscount);
        this.kindOfMeat = kindOfMeat;
    }

    public Meat(String name, Calendar expaireDate, Calendar createDate, double price, boolean disscount, String kindOfMeat, boolean canReproduct, boolean neededMinusTemperature) {
        super(name, expaireDate, createDate, price, disscount, canReproduct, neededMinusTemperature);
        this.kindOfMeat = kindOfMeat;
    }
}
