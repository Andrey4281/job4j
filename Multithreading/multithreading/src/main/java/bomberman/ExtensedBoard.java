package bomberman;

import java.util.List;
import java.util.Map;

public class ExtensedBoard extends BoardImpl {

    public ExtensedBoard(int sizeX, int sizeY, Map<Cell, String> characteristicCells) {
        super(sizeX, sizeY, characteristicCells);
    }

    @Override
    public List<Cell> getListNeighboringCells(Cell currentCell) {
        List<Cell> list = super.getListNeighboringCells(currentCell);
        list.stream().forEach(cell->{
            if (characteristicCells.get(cell) != null && characteristicCells.get(cell).equals("Stone")) {
                list.remove(cell);
            }
        });
        return list;
    }
}
