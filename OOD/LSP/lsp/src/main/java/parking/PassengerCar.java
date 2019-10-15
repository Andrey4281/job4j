package parking;

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
        boolean res = false;
        if (parking.getCountFreePlaceForPassengerCar() >= 1) {
            res = parking.placeInPlacesForPassengerCar(this);
        }
        return res;
    }
}
