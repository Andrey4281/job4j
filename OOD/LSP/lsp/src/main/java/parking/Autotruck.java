package parking;

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
        if (parking.getCountFreePlaceForAutotruck() >= 1) {
            res = parking.placeInPlacesForAutotruck(this);
        } else if (parking.getCountFreePlaceForPassengerCar() >= getSizeOfCar()) {
            res = parking.placeInPlacesForPassengerCar(this);
        }
        return res;
    }
}
