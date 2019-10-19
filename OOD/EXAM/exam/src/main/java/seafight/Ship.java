package seafight;

import java.util.List;

public interface Ship {
    int getCountOfLives();
    void setCountOfLives(int lives);
    void setCoordinatesForShipOnField(List<Point> coordinates);
}
