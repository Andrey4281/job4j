package bomberman;

import java.util.List;

public class Player implements Runnable {
    private volatile Cell currentPosition;
    private final Board board;

    public Player(Cell currentPosition, Board board) {
        this.currentPosition = currentPosition;
        this.board = board;
    }

    @Override
    public void run() {
        board.lockCurrentCell(currentPosition);
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.println("Current position:");
                System.out.println(String.format("X = %s; Y = %s", currentPosition.getX(), currentPosition.getY()));
                Thread.currentThread().sleep(1000);
                boolean isMove = false;
                List<Cell> list = board.getListNeighboringCells(currentPosition);
                while (!isMove) {
                    for (Cell cell: list) {
                        isMove = board.move(currentPosition, cell);
                        if (isMove) {
                            currentPosition = cell;
                            break;
                        }
                    }
                }
                if (board.isFindExit(currentPosition)) {
                    Thread.currentThread().interrupt();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
