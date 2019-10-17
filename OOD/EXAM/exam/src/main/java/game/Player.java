package game;


/**
 * Base interface for description logic of players
 * @author asemenov
 * @since 12.10.2019
 * @version 1.0
 */
public interface Player {
    void makeMove();
    char getSignOfPlayer();
    void setGameField(GameField field);
}
