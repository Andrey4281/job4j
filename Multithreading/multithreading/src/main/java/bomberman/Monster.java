package bomberman;

import java.util.List;

public class Monster extends Player {
    private final BomberMan bomberMan;

    public Monster(Cell currentPosition, Board board, BomberMan bomberMan) {
        super(currentPosition, board);
        this.bomberMan = bomberMan;
    }


    @Override
    protected void playerLogic() throws InterruptedException {
        Thread.currentThread().sleep(1000);
        if (bomberMan.isEaten || bomberMan.isWin) {
            Thread.currentThread().interrupt();
        } else {
            boolean isMove = false;
            List<Cell> list = board.getListNeighboringCells(currentPosition);
            while (!isMove && !bomberMan.isEaten && !bomberMan.isWin) {
                for (Cell cell: list) {
                    isMove = board.move(currentPosition, cell);
                    if (isMove) {
                        currentPosition = cell;
                        break;
                    } else {
                        if (cell.equals(bomberMan.currentPosition)) {
                            bomberMan.isEaten = true;
                            break;
                        } else if (bomberMan.isWin) {
                            break;
                        }
                    }
                }
            }
        }
    }
}
