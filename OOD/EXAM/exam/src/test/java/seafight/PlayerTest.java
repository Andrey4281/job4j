package seafight;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PlayerTest {
    private static final String LN = System.getProperty("line.separator");
    Player playerOne;
    Player playerTwo;

    @Before
    public void setPlayers() {
        playerOne = null;
        Field fieldForBattleOne = null;
        fieldForBattleOne.inicialize(2);
        playerOne.setFieldForBattle(fieldForBattleOne);

        playerTwo = null;
        Field fieldForBattleTwo = null;
        fieldForBattleTwo.inicialize(2);
        playerOne.setFieldForBattle(fieldForBattleTwo);
    }

    @Test
    public void whenPlaceShipOnFieldShouldGetCoordinatesOfShipAndCountOfShips() {
        List<Ship> ships = new LinkedList<>();
        Ship ship = null;
        List<Point> coordinates = new LinkedList<>();
        coordinates.add(new Point(0, 0));
        coordinates.add(new Point(0, 1));
        ship.setCoordinatesForShipOnField(coordinates);
        ships.add(ship);

        playerOne.placeShips(ships);
        playerOne.getField().showField();

        assertThat(playerOne.getCountSurvivedShips(), is(1));
        assertThat(playerOne.getCoordinatesOfShips().size(), is(2));
        assertEquals(playerOne.getCoordinatesOfShips().get(new Point(0, 0)), ship);
        assertEquals(playerOne.getCoordinatesOfShips().get(new Point(0, 1)), ship);
    }

    @Test
    public void whenPlayerOneMakeThreeMoveHeShouldChangeCountOfShipsOfTwoPlayerAndStateOfShipsAndStateFieldForBattle() {
        List<Ship> ships = new LinkedList<>();
        Ship shipOne = null;
        List<Point> coordinatesOne = new LinkedList<>();
        coordinatesOne.add(new Point(1, 0));
        coordinatesOne.add(new Point(1, 1));
        shipOne.setCoordinatesForShipOnField(coordinatesOne);
        Ship shipTwo = null;
        List<Point> coordinatesTwo = new LinkedList<>();
        coordinatesTwo.add(new Point(0, 0));
        shipTwo.setCoordinatesForShipOnField(coordinatesTwo);
        ships.add(shipOne);
        ships.add(shipTwo);
        playerTwo.placeShips(ships);

        playerOne.makeAMove(playerTwo);
        playerOne.makeAMove(playerTwo);
        playerOne.makeAMove(playerTwo);

        assertThat(playerTwo.getCoordinatesOfShips().get(new Point(0, 0)).getCountOfLives(), is(0));
        assertThat(playerTwo.getCountSurvivedShips(), is(1));
        assertThat(playerTwo.getField().getMarkOfCell(0, 0), is('k'));
        assertThat(playerTwo.getField().getMarkOfCell(0, 1), is('*'));
        assertThat(playerTwo.getField().getMarkOfCell(1, 0), is('i'));
        assertThat(playerTwo.getField().getMarkOfCell(1, 1), is('-'));
        assertThat(playerTwo.getCoordinatesOfShips().get(new Point(1, 0)).getCountOfLives(), is(1));
        assertThat(playerTwo.getCoordinatesOfShips().get(new Point(1, 1)).getCountOfLives(), is(1));
    }
}