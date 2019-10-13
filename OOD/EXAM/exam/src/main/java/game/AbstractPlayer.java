package game;

public abstract class AbstractPlayer implements Player {
    protected final char signOfPlayer;
    protected GameField gameField;

    protected AbstractPlayer(char signOfPlayer) {
        this.signOfPlayer = signOfPlayer;
    }

    @Override
    public char getSignOfPlayer() {
        return this.signOfPlayer;
    }

    @Override
    public void setGameField(GameField field) {
        this.gameField = field;
    }
}
