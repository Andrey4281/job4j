package game;

/**
 * Gegeralized rules for game.
 * @author asemenov
 * @since 13.10.2019
 * @version 1
 */
public class GeneralizedRules implements RulesOfGame {
    private final GameField gameField;
    private final int sequenceLength;

    public GeneralizedRules(GameField field, int sequenceLength) {
        gameField = field;
        this.sequenceLength = sequenceLength;
    }

    @Override
    public boolean IfWin(char signOfPlayer) {
        return linearCheck(signOfPlayer, (i, j)->gameField.getPosition(i, j)) ||
                linearCheck(signOfPlayer, (i, j)->gameField.getPosition(j, i)) ||
                diagonalCheck(signOfPlayer);
    }

    @Override
    public boolean IfDraw() {
        return gameField.isFilled();
    }


    private boolean linearCheck(char signOfPlayer, accesToCell acces) {
        boolean result = false;
        for (int i = 0; i < gameField.getSize(); i++) {
            for (int j = 0; j < gameField.getSize(); j++) {
                if (acces.getCell(i, j) == signOfPlayer) {
                    int currentLenghtOfSigns = 1;
                    ++j;
                    while (j < gameField.getSize() &&
                            acces.getCell(i, j) == signOfPlayer &&
                            currentLenghtOfSigns < sequenceLength) {
                        ++currentLenghtOfSigns;
                        ++j;
                    }
                    if (currentLenghtOfSigns >= sequenceLength) {
                        result = true;
                    }
                }
                if (result) {
                    break;
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    private boolean diagonalCheck(char signOfPlayer) {
        return upperOrDownDiagonalCheck(signOfPlayer, (i, k)->gameField.getPosition(i, k))
                || upperOrDownDiagonalCheck(signOfPlayer, (i, k)->gameField.getPosition(k, i))
                || reverseDiagonalCheck(signOfPlayer);
    }

    private boolean upperOrDownDiagonalCheck(char signOfPlayer, accesToCell acces) {
        boolean result = false;
        for (int j = gameField.getSize() - sequenceLength; j >= 0; j--) {
            for (int i = 0, k = j; i < gameField.getSize() && k < gameField.getSize(); i++, k++) {
                if (acces.getCell(i, k) == signOfPlayer) {
                    int currentLenghtOfSigns = 1;
                    ++i;
                    ++k;
                    while (i < gameField.getSize() && k < gameField.getSize() &&
                            acces.getCell(i, k) == signOfPlayer &&
                            currentLenghtOfSigns < sequenceLength) {
                        ++currentLenghtOfSigns;
                        ++i;
                        ++k;
                    }
                    if (currentLenghtOfSigns >= sequenceLength) {
                        result = true;
                    }
                }
                if (result) {
                    break;
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    private boolean reverseDiagonalCheck(char signOfPlayer) {
        return upperReverseDiagonalCheck(signOfPlayer) || downReverseDiagonalCheck(signOfPlayer);
    }

    private boolean upperReverseDiagonalCheck(char signOfPlayer) {
        boolean result = false;
        for (int j = gameField.getSize() - sequenceLength; j < gameField.getSize(); j++) {
            for (int i = j, k = 0; i >=0 && k < gameField.getSize(); i--, k++) {
                if (gameField.getPosition(i, k) == signOfPlayer) {
                    int currentLenghtOfSigns = 1;
                    --i;
                    ++k;
                    while (i >= 0 && k < gameField.getSize() &&
                            gameField.getPosition(i, k) == signOfPlayer &&
                            currentLenghtOfSigns < sequenceLength) {
                        ++currentLenghtOfSigns;
                        --i;
                        ++k;
                    }
                    if (currentLenghtOfSigns >= sequenceLength) {
                        result = true;
                    }
                }
                if (result) {
                    break;
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    private boolean downReverseDiagonalCheck(char signOfPlayer) {
        boolean result = false;
        for (int j = gameField.getSize() - sequenceLength; j > 0; j--) {
            for (int i = gameField.getSize() - 1, k = j; i >=0 && k < gameField.getSize(); i--, k++) {
                if (gameField.getPosition(i, k) == signOfPlayer) {
                    int currentLenghtOfSigns = 1;
                    --i;
                    ++k;
                    while (i >= 0 && k < gameField.getSize() &&
                            gameField.getPosition(i, k) == signOfPlayer &&
                            currentLenghtOfSigns < sequenceLength) {
                        ++currentLenghtOfSigns;
                        --i;
                        ++k;
                    }
                    if (currentLenghtOfSigns >= sequenceLength) {
                        result = true;
                    }
                }
                if (result) {
                    break;
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }
}


