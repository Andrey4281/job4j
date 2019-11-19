package bomberman;

public class BomberMan extends Player {
    private final UserOutput output;
    private final UserInput input;
    protected volatile boolean isEaten = false;
    protected volatile boolean isWin = false;

    public BomberMan(Cell currentPosition, Board board, UserOutput output, UserInput input) {
        super(currentPosition, board);
        this.output = output;
        this.input = input;
    }

    @Override
    protected void playerLogic() throws InterruptedException {
        Thread.currentThread().sleep(1000);
        if (isEaten) {
            output.showMessage("You were eaten!");
            isEaten = true;
            Thread.currentThread().interrupt();
        } else {
            Cell move = input.getCellFromUser();
            if (board.moveIsStone(move)) {
                Thread.currentThread().sleep(500);
            } else {
                boolean isMoving = board.move(currentPosition, move);
                if (!isMoving) {
                    output.showMessage("You were eaten");
                    isEaten = true;
                    Thread.currentThread().interrupt();
                } else {
                    currentPosition = move;
                }
            }
            if (board.isFindExit(currentPosition)) {
                output.showMessage("You win!");
                isWin = true;
                Thread.currentThread().interrupt();
            }
        }
    }
}
