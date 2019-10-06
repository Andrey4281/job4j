package strorage;

import java.util.Calendar;
import java.util.Date;

public class Food {
    private int id_food;
    private final String name;
    private final Calendar expaireDate;
    private final Calendar createDate;
    private final double price;
    private boolean disscount;
    private int id_storage_for_food;
    private boolean canReproduct;
    private boolean neededMinusTemperature;

    public Food(String name, Calendar expaireDate, Calendar createDate, double price, boolean disscount) {
        this.name = name;
        this.expaireDate = expaireDate;
        this.createDate = createDate;
        this.price = price;
        this.disscount = disscount;
    }

    public Food(String name, Calendar expaireDate, Calendar createDate, double price, boolean disscount, boolean canReproduct, boolean neededMinusTemperature) {
        this(name, expaireDate, createDate, price, disscount);
        this.canReproduct = canReproduct;
        this.neededMinusTemperature = neededMinusTemperature;
    }

    public double getPercentOfExpaireDate() {
        return ((double)(new Date().getTime() - createDate.getTime().getTime())) / (expaireDate.getTime().getTime() - createDate.getTime().getTime()) * 100;
    }

    public void setDisscount(boolean disscount) {
        this.disscount = disscount;
    }

    public String getName() {
        return name;
    }

    public boolean getDisscount() {
        return disscount;
    }

    public void setCanReproduct(boolean canReproduct) {
        this.canReproduct = canReproduct;
    }

    public boolean getCanReproduct() {
        return  canReproduct;
    }

    public void setNeededMinusTemperature(boolean neededMinusTemperature) {
        this.neededMinusTemperature = neededMinusTemperature;
    }

    public boolean getneededMinusTemperature() {
        return neededMinusTemperature;
    }

}
