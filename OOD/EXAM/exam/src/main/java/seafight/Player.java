package seafight;

import java.util.List;
import java.util.Map;

public interface Player {
    boolean makeAMove(Player opponent);
    void placeShips(List<Ship> ships);
    int getCountSurvivedShips();
    void setCountSurvivedShips(int count);
    void setFieldForBattle(Field fieldForBattle);
    Field getField();
    Map<Point, Ship> getCoordinatesOfShips();
}
