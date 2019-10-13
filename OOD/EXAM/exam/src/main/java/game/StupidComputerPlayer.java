package game;

/**
 * This class represent a simple computer game strategy.
 * @author asemenov
 * @since 12.10.2019
 * @version 1
 */
public class StupidComputerPlayer extends AbstractPlayer {

    public StupidComputerPlayer(char signOfComputer) {
        super(signOfComputer);
    }

    @Override
    public void makeMove() {
        boolean isExit = false;
        for (int i = 0; i < gameField.getSize(); i++) {
            for (int j = 0; j < gameField.getSize(); j++) {
                if (!gameField.getIsMarked(i, j)) {
                    gameField.markPosition(i, j, signOfPlayer);
                    isExit = true;
                    break;
                }
            }
            if (isExit) {
                break;
            }
        }
    }
}
