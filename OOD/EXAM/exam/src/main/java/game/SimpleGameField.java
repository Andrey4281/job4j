package game;

public class SimpleGameField implements GameField {
    private static final String LN = System.getProperty("line.separator");
    private final char[][] field;
    private final boolean[][] isMarked;
    private int countOfMarked = 0;
    private int sizeOfField = 3;

    public SimpleGameField() {
        this.field = new char[this.sizeOfField][this.sizeOfField];
        this.isMarked = new boolean[this.sizeOfField][this.sizeOfField];
    }

    public SimpleGameField(int sizeOfField) {
        this.sizeOfField = sizeOfField;
        this.field = new char[this.sizeOfField][this.sizeOfField];
        this.isMarked = new boolean[this.sizeOfField][this.sizeOfField];
    }

    @Override
    public void markPosition(int i, int j, char signOfMark) {
        if (i < 0 || i >= sizeOfField || j < 0 || j >= sizeOfField) {
            throw new GameException(String.format("Indexes of position must be between 0 to %s", sizeOfField));
        }
        this.field[i][j] = signOfMark;
        this.isMarked[i][j] = true;
        countOfMarked++;
    }

    @Override
    public char getPosition(int i, int j) {
        if (i < 0 || i >= sizeOfField || j < 0 || j >= sizeOfField) {
            throw new GameException(String.format("Indexes of position must be between 0 to %s", sizeOfField));
        }
        return this.field[i][j];
    }

    @Override
    public int getSize() {
        return sizeOfField;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < sizeOfField; i++) {
            for (int j = 0; j < sizeOfField; j++) {
                res.append(field[i][j]).append(" ");
            }
            res.append(LN);
        }
        return res.toString();
    }

    @Override
    public boolean getIsMarked(int i, int j) {
        if (i < 0 || i >= sizeOfField || j < 0 || j >= sizeOfField) {
            throw new GameException(String.format("Indexes of position must be between 0 to %s", sizeOfField));
        }
        return isMarked[i][j];
    }

    @Override
    public boolean isFilled() {
        return countOfMarked == sizeOfField * sizeOfField;
    }
}
