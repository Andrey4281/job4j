package seafight;

import java.util.Map;

public interface GameMechanic {
    void configureGame(int sizeOfField, Map<Integer, Integer> ships, Player one, Player two);
    void start();
}
