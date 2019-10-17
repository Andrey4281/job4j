package game;

/**
 * Rules of game three by three.
 * @author asemenov
 * @since 12.10.2019
 * @version 1
 */
public class RulesOfGameThreeByThree implements RulesOfGame {
    private final GameField gameField;

    public RulesOfGameThreeByThree(GameField field) {
        gameField = field;
    }

    @Override
    public boolean IfWin(char signOfPlayer) {
        return (gameField.getPosition(0, 0) == signOfPlayer
                && gameField.getPosition(0, 1) == signOfPlayer
        && gameField.getPosition(0, 2) == signOfPlayer) ||
                (gameField.getPosition(1, 0) == signOfPlayer
                        && gameField.getPosition(1, 1) == signOfPlayer
                        && gameField.getPosition(1, 2) == signOfPlayer) ||
                (gameField.getPosition(2, 0) == signOfPlayer
                        && gameField.getPosition(2, 1) == signOfPlayer
                        && gameField.getPosition(2, 2) == signOfPlayer) ||
                (gameField.getPosition(0, 0) == signOfPlayer
                        && gameField.getPosition(1, 0) == signOfPlayer
                        && gameField.getPosition(2, 0) == signOfPlayer) ||
                (gameField.getPosition(0, 1) == signOfPlayer
                        && gameField.getPosition(1, 1) == signOfPlayer
                        && gameField.getPosition(2, 1) == signOfPlayer) ||
                (gameField.getPosition(0, 2) == signOfPlayer
                        && gameField.getPosition(1, 2) == signOfPlayer
                        && gameField.getPosition(2, 2) == signOfPlayer) ||
                (gameField.getPosition(0, 0) == signOfPlayer
                        && gameField.getPosition(1, 1) == signOfPlayer
                        && gameField.getPosition(2, 2) == signOfPlayer) ||
                (gameField.getPosition(2, 0) == signOfPlayer
                        && gameField.getPosition(1, 1) == signOfPlayer
                        && gameField.getPosition(0, 2) == signOfPlayer);
    }

    @Override
    public boolean IfDraw() {
        return gameField.isFilled();
    }
}
