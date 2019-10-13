package game;

import com.google.common.base.Joiner;

/**
 * Realization of player for person for console version of game.
 * @author asemenov
 * @since 12.10.2019
 * @version 1
 */
public class PlayerPersonConsole extends AbstractPlayer {
    private static final String LN = System.getProperty("line.separator");
    private final Console console;

    public PlayerPersonConsole(Console console, char signOfPerson) {
        super(signOfPerson);
        this.console = console;
    }

    @Override
    public void makeMove() {
        boolean coorrect = false;
        while (!coorrect) {
            console.output(Joiner.on(LN).join("Enter the coordinates of cell of field by following way: x y", ""));
            String coordinates = console.intput();
            if (!coordinates.matches("\\s*\\d+\\s+\\d+\\s*")) {
                throw new GameException(Joiner.on(LN).join("Invalid input of coordinates. You should enter the coordinates as: x y", ""));
            }
            String[] s_coordinates = coordinates.split("\\s+");
            int x = Integer.parseInt(s_coordinates[0]);
            int y = Integer.parseInt(s_coordinates[1]);
            if (gameField.getIsMarked(x, y)) {
                console.output(Joiner.on(LN).join("This cell is marked. Try to choose  another cell.", ""));
            } else {
                gameField.markPosition(x, y, this.signOfPlayer);
                coorrect = true;
            }
        }
    }
}
