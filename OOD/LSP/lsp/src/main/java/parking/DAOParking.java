package parking;

import java.util.List;

public interface DAOParking {
    void createDataBaseForParking(int countPlaceForPassengerCar, int countPlaceForAutotruck);
    void addCarToPlaceForAutotruck(Car car);
    boolean addCarToPlaceForPassengerCar(Car car);
    boolean removeCarFromDataBase(Car car);
    List<ParkingPlace> getPlaceWhereLocatedCar(Car car);
}
