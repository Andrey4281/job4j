package game;

public interface GameField {
    void markPosition(int i, int j, char signOfMark);
    char getPosition(int i, int j);
    boolean getIsMarked(int i, int j);
    boolean isFilled();
    int getSize();
}
