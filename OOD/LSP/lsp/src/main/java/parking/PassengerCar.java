package parking;

import java.util.Objects;

public class PassengerCar implements Car {
    private final String name;

    public PassengerCar(String name) {
        this.name = name;
    }

    @Override
    public String getNameOfCar() {
        return name;
    }

    @Override
    public int getSizeOfCar() {
        return 1;
    }

    @Override
    public boolean takeParkingSpace(Parking parking) {
        return parking.placeInPlacesForPassengerCar(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PassengerCar that = (PassengerCar) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
