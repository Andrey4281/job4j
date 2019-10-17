package parking;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CarTest {

    @Test
    public void sequenceOfTakeParkingSpaceMethodInvocations() {
        Parking parking = null;
        parking.initialize(3, 1);
        Car carOne = new PassengerCar("carOne");
        Car carTwo = new Autotruck("carTwo", 2);
        Car carThree = new PassengerCar("carThree");
        Car carFour = new Autotruck("carFour", 10);

        assertThat(carOne.takeParkingSpace(parking), is(true));
        assertThat(carFour.takeParkingSpace(parking), is(true));
        assertThat(carTwo.takeParkingSpace(parking), is(true));
        assertThat(carThree.takeParkingSpace(parking), is(false));
    }
}