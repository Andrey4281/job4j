package model;

import java.util.Objects;

public class Item {
    private String firstName;
    private String secondName;
    private String sex;
    private String description;

    public Item() {

    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(firstName, item.firstName)
                && Objects.equals(secondName, item.secondName)
                && Objects.equals(sex, item.sex)
                && Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName, sex, description);
    }

    @Override
    public String toString() {
        return "Item{"
                + "firstName='" + firstName + '\''
                + ", secondName='" + secondName + '\''
                + ", sex='" + sex + '\''
                + ", description='" + description + '\''
                + '}';
    }
}
