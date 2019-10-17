package game;

public abstract class AbstractGameMechanic {
    protected final Player playerOne;
    protected final Player playerTwo;
    protected final GameField field;
    protected final RulesOfGame rules;


    public AbstractGameMechanic(Player playerOne, Player playerTwo, GameField field, RulesOfGame rules) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.playerOne.setGameField(field);
        this.playerTwo.setGameField(field);
        this.field = field;
        this.rules = rules;
    }

    abstract public void start();
}
