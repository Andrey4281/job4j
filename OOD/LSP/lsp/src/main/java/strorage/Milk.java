package strorage;

import java.util.Calendar;

public class Milk extends Food {
    private final double percentOfFat;

    public Milk(String name, Calendar expaireDate, Calendar createDate, double price, boolean disscount, double percentOfFat) {
        super(name, expaireDate, createDate, price, disscount);
        this.percentOfFat = percentOfFat;
    }

    public Milk(String name, Calendar expaireDate, Calendar createDate, double price, boolean disscount, double percentOfFat, boolean canReproduct, boolean neededMinusTemperature) {
        super(name, expaireDate, createDate, price, disscount, canReproduct, neededMinusTemperature);
        this.percentOfFat = percentOfFat;
    }
}
