package seafight;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

public class GameMechanicTest {
    private Player playerOne;
    private Player playerTwo;
    private int sizeOfField;
    private GameMechanic gameMechanic;

    @Before
    public void setPlayers() {
        playerOne = null;
        playerTwo = null;
        sizeOfField = 3;
        gameMechanic = null;
    }

    @Test
    public void whenTwoComputerPlayOnSmallFieldShouldWinOneComputer() {
        Map<Integer, Integer> ships = new LinkedHashMap<>();
        ships.put(2, 1);
        ships.put(1, 1);

        gameMechanic.configureGame(sizeOfField, ships, playerOne, playerTwo);
        gameMechanic.start();

        assertThat(playerTwo.getCountSurvivedShips(), is(0));
        assertThat(playerTwo.getField().getMarkOfCell(0, 0), is('k'));
        assertThat(playerTwo.getField().getMarkOfCell(0, 1), is('k'));
        assertThat(playerTwo.getField().getMarkOfCell(0, 2), is('*'));
        assertThat(playerTwo.getField().getMarkOfCell(1, 0), is('*'));
        assertThat(playerTwo.getField().getMarkOfCell(1, 1), is('*'));
        assertThat(playerTwo.getField().getMarkOfCell(1, 2), is('*'));
        assertThat(playerTwo.getField().getMarkOfCell(2, 0), is('k'));
        assertThat(playerTwo.getField().getMarkOfCell(2, 1), is('-'));
        assertThat(playerTwo.getField().getMarkOfCell(2, 2), is('-'));

        assertThat(playerOne.getCountSurvivedShips(), is(1));
        assertThat(playerOne.getField().getMarkOfCell(0, 0), is('k'));
        assertThat(playerOne.getField().getMarkOfCell(0, 1), is('k'));
        assertThat(playerOne.getField().getMarkOfCell(0, 2), is('*'));
        assertThat(playerOne.getField().getMarkOfCell(1, 0), is('*'));
        assertThat(playerOne.getField().getMarkOfCell(1, 1), is('*'));
        assertThat(playerOne.getField().getMarkOfCell(1, 2), is('*'));
        assertThat(playerOne.getField().getMarkOfCell(2, 0), is('-'));
        assertThat(playerOne.getField().getMarkOfCell(2, 1), is('-'));
        assertThat(playerOne.getField().getMarkOfCell(2, 2), is('-'));
    }
}