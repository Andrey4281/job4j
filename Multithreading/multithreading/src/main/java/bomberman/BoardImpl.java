package bomberman;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoardImpl implements Board {
    private final Lock[][] locks;
    private final int sizeX;
    private final int sizeY;
    private final Map<Cell, String> characteristicCells;

    public BoardImpl(int sizeX, int sizeY, Map<Cell, String> characteristicCells) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.characteristicCells = characteristicCells;
        locks = new Lock[sizeX][sizeY];
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                locks[i][j] = new ReentrantLock();
            }
        }
    }

    @Override
    public boolean move(Cell source, Cell dist) throws InterruptedException {
        boolean temp = locks[dist.getX()][dist.getY()].tryLock(500, TimeUnit.MILLISECONDS);
        if (temp) {
            locks[source.getX()][source.getY()].unlock();
        }
        return temp;
    }

    @Override
    public List<Cell> getListNeighboringCells(Cell currentCell) {
        List<Cell> list = new LinkedList<>();
        if (currentCell.getX() >= 0 && currentCell.getX() < sizeX - 1) {
            list.add(new Cell(currentCell.getX() + 1, currentCell.getY()));
        }
        if (currentCell.getX() > 0 && currentCell.getX() <= sizeX - 1) {
            list.add(new Cell(currentCell.getX() - 1, currentCell.getY()));
        }
        if (currentCell.getY() > 0 && currentCell.getY() <= sizeY - 1) {
            list.add(new Cell(currentCell.getX(), currentCell.getY() - 1));
        }
        if (currentCell.getY() >=0 && currentCell.getY() < sizeY - 1) {
            list.add(new Cell(currentCell.getX(), currentCell.getY() +1));
        }
        return list;
    }

    @Override
    public void lockCurrentCell(Cell currentCell) {
        locks[currentCell.getX()][currentCell.getY()].lock();
    }

    @Override
    public boolean isFindExit(Cell currentCell) {
        boolean res = false;
        if (characteristicCells.get(currentCell) != null
                && characteristicCells.get(currentCell).equals("Exit")) {
            res = true;
        }
        return res;
    }
}
