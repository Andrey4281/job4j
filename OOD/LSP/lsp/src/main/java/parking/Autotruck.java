package parking;

import java.util.Objects;

public class Autotruck implements Car {
    private final String name;
    private final int size;

    public Autotruck(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getNameOfCar() {
        return name;
    }

    @Override
    public int getSizeOfCar() {
        return size;
    }

    @Override
    public boolean takeParkingSpace(Parking parking) {
        boolean res = false;
        if (parking.placeInPlacesForAutotruck(this)) {
            res = true;
        } else {
            res = parking.placeInPlacesForPassengerCar(this);
        }
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autotruck autotruck = (Autotruck) o;
        return size == autotruck.size &&
                Objects.equals(name, autotruck.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size);
    }
}
