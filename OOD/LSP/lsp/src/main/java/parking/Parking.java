package parking;

import java.util.List;

public interface Parking {
    void initialize(int countPlaceForPassengerCar, int countPlaceForAutotruck);
    int getCountFreePlaceForPassengerCar();
    int getCountFreePlaceForAutotruck();
    boolean placeInPlacesForPassengerCar(Car passengerCar);
    void placeInPlacesForAutotruck(Car autotruck);
    boolean removeCarFromParking(Car car);
    List<ParkingPlace> getPlaceWhereLocatedCar(Car car);
}
