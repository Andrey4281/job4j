package parking;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ParkingTest {

    @Test
    public void sequenceOfPlaceInPlacesForPassengerCarMethodInvocations() {
        Parking parking = null;
        parking.initialize(3, 0);
        Car carOne = new PassengerCar("carOne");
        Car carTwo = new Autotruck("carTwo", 2);

        assertThat(parking.placeInPlacesForPassengerCar(carOne) , is(true));
        assertThat(parking.placeInPlacesForPassengerCar(carTwo) , is(true));
        assertThat(parking.getCountFreePlaceForPassengerCar(), is(0));
    }

    @Test
    public void whenCallPlaceInPlacesForAutotruckShouldReduceCountOfPlacesForAutotruck() {
        Parking parking = null;
        parking.initialize(0, 1);
        Car car = new Autotruck("car", 2);

        assertThat(parking.placeInPlacesForAutotruck(car), is(true));
        assertThat(parking.getCountFreePlaceForAutotruck(), is(1));
    }

    @Test
    public void sequenceOfRemoveCarFromParkingMethodInvocation() {
        Parking parking = null;
        parking.initialize(1, 1);
        Car carOne = new PassengerCar("carOne");
        Car carTwo = new Autotruck("carTwo", 2);

        parking.placeInPlacesForPassengerCar(carOne);
        parking.placeInPlacesForAutotruck(carTwo);
        parking.removeCarFromParking(carOne);
        parking.removeCarFromParking(carTwo);

        assertThat(parking.getCountFreePlaceForPassengerCar(), is(1));
        assertThat(parking.getCountFreePlaceForAutotruck(), is(1));
    }

    @Test
    public void whenPlaceCarShouldReturnAfterGetPlaceWhereLocatedCarMethodInvocationListOfPlaces() {
        Parking parking = null;
        parking.initialize(1, 1);
        Car carOne = new PassengerCar("carOne");
        Car carTwo = new Autotruck("carThree", 10);

        parking.placeInPlacesForPassengerCar(carOne);
        parking.placeInPlacesForAutotruck(carTwo);
        List<Integer> placesForFirstCar = parking.getPlaceWhereLocatedCar(carOne);
        List<Integer> placesForTwoCar = parking.getPlaceWhereLocatedCar(carTwo);

        assertThat(placesForFirstCar.size(), is(1));
        assertThat(placesForFirstCar.iterator().next(), is(1));
        assertThat(placesForTwoCar.size(), is(1));
        assertThat(placesForTwoCar.iterator().next(), is(2));
    }
}