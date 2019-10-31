package parking;

import java.util.List;

public interface Parking {
    void initialize(int countPlaceForPassengerCar, int countPlaceForAutotruck);
    int getCountFreePlaceForPassengerCar();
    int getCountFreePlaceForAutotruck();
    boolean placeInPlacesForPassengerCar(Car car);
    boolean placeInPlacesForAutotruck(Car autotruck);
    boolean removeCarFromParking(Car car);
    List<Integer> getPlaceWhereLocatedCar(Car car);
}
