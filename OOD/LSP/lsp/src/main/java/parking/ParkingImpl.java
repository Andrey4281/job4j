package parking;

import java.util.*;

public class ParkingImpl implements Parking {
    static private final class PlaceForPassengerCar {
        private boolean isOccuped = false;
        private final int number;

        private PlaceForPassengerCar(int number) {
            this.number = number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PlaceForPassengerCar that = (PlaceForPassengerCar) o;
            return number == that.number;
        }

        @Override
        public int hashCode() {
            return Objects.hash(number);
        }
    }


    private final Map<Car, List<Integer>> cars = new HashMap<>();
    private final Map<Integer, PlaceForPassengerCar> placesForPassengerCar = new LinkedHashMap<>();
    private final Set<Integer> freePlacesForAutotruck = new HashSet<>();
    private final Set<Integer> occupiedPlacesForAutotruck = new HashSet<>();
    private int countFreePlacesForPassengerCar;


    @Override
    public void initialize(int countPlaceForPassengerCar, int countPlaceForAutotruck) {
        this.countFreePlacesForPassengerCar = countPlaceForPassengerCar;

        for (int i = 1; i <= countPlaceForPassengerCar; i++) {
            placesForPassengerCar.put(i, new PlaceForPassengerCar(i));
        }

        int totalCountOfPlace = countPlaceForPassengerCar + countPlaceForAutotruck;
        for (int i = countPlaceForPassengerCar + 1; i <= totalCountOfPlace; i++) {
            freePlacesForAutotruck.add(i);
        }
    }

    @Override
    public int getCountFreePlaceForPassengerCar() {
        return countFreePlacesForPassengerCar;
    }

    @Override
    public int getCountFreePlaceForAutotruck() {
        return freePlacesForAutotruck.size();
    }

    @Override
    public boolean placeInPlacesForPassengerCar(Car car) {
        boolean res = false;
        if (car.getSizeOfCar() <= countFreePlacesForPassengerCar) {
            res = true;
            countFreePlacesForPassengerCar -= car.getSizeOfCar();
            PlaceForPassengerCar lastPlaceOfSequence = getLastPlaceFromSequenceOfPlaces(car);
            int moving = lastPlaceOfSequence.number - car.getSizeOfCar() + 1;
            List<Integer> placesForCar = new LinkedList<>();
            for (int i = lastPlaceOfSequence.number; i>= moving; i--) {
                placesForPassengerCar.get(i).isOccuped = true;
                placesForCar.add(i);
            }
            cars.put(car, placesForCar);
        }
        return res;
    }

    private PlaceForPassengerCar getLastPlaceFromSequenceOfPlaces(Car car) {
        Iterator<Integer> iterator = placesForPassengerCar.keySet().iterator();
        PlaceForPassengerCar lastPlaceOfSequence = null;
        int minlength = Integer.MAX_VALUE;
        while (iterator.hasNext()) {
            PlaceForPassengerCar currentPlace = placesForPassengerCar.get(iterator.next());
            if (!currentPlace.isOccuped) {
                int currentLength = 1;
                while (iterator.hasNext()) {
                    PlaceForPassengerCar currentNotOccupiedPlace = placesForPassengerCar.get(iterator.next());
                    if (currentNotOccupiedPlace.isOccuped) break;
                    currentPlace = currentNotOccupiedPlace;
                    currentLength++;
                }
                if (currentLength>= car.getSizeOfCar() && currentLength < minlength) {
                    minlength = currentLength;
                    lastPlaceOfSequence = currentPlace;
                }
            }
        }
        return lastPlaceOfSequence;
    }

    @Override
    public boolean placeInPlacesForAutotruck(Car autotruck) {
        boolean res = false;
        if (freePlacesForAutotruck.iterator().hasNext()) {
            res = true;
            int placeNumber = freePlacesForAutotruck.iterator().next();
            freePlacesForAutotruck.remove(placeNumber);
            occupiedPlacesForAutotruck.add(placeNumber);
            cars.put(autotruck, new LinkedList<>(Arrays.asList(new Integer[]{placeNumber})));
        }
        return res;
    }

    @Override
    public boolean removeCarFromParking(Car car) {
        boolean res = false;
        if (cars.get(car) != null) {
            res = true;
            List<Integer> listOfPlaces = cars.remove(car);
            Optional<Integer> numberOfPlace = listOfPlaces.stream().max(Integer::compareTo);
            if (numberOfPlace.get() <= placesForPassengerCar.size()) {
                listOfPlaces.stream().forEach(value -> placesForPassengerCar.get(value).isOccuped = false);
                countFreePlacesForPassengerCar += car.getSizeOfCar();
            } else {
                occupiedPlacesForAutotruck.remove(listOfPlaces.iterator().next());
                freePlacesForAutotruck.add(listOfPlaces.iterator().next());
            }
        }
        return res;
    }

    @Override
    public List<Integer> getPlaceWhereLocatedCar(Car car) {
        return cars.get(car);
    }
}
