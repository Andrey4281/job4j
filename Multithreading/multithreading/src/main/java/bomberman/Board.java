package bomberman;

import java.util.List;

public interface Board {
    boolean move(Cell source, Cell dist) throws InterruptedException;
    boolean isFindExit(Cell currentCell);
    boolean moveIsStone(Cell currentCell);
    List<Cell> getListNeighboringCells(Cell currentCell);
    void lockCurrentCell(Cell currentCell);
}
