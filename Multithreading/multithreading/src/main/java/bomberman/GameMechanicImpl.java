package bomberman;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameMechanicImpl implements GameMechanic {
    private final List<Runnable> players;
    private final ExecutorService pool = Executors.newCachedThreadPool();

    public GameMechanicImpl(List<Runnable> players) {
        this.players = players;
    }

    @Override
    public void beginGame() {
        for (Runnable player: players) {
            pool.execute(player);
        }
    }

    @Override
    public void stopGame() {
        pool.shutdownNow();
    }

    public static void main(String[] args) throws InterruptedException {
        Map<Cell, String> map = new HashMap<>();
        map.put(new Cell(4, 4), "Exit");
        map.put(new Cell(3, 3), "Stone");
        Board board = new ExtensedBoard(5, 5, map);
        BomberMan bomberMan = new BomberMan(new Cell(0, 0), board, null, null);
        Player monster = new Monster(new Cell(0, 3), board, bomberMan);

        List<Runnable> tasks = new LinkedList<>();
        tasks.add(bomberMan);
        tasks.add(monster);

        GameMechanic gameMechanic = new GameMechanicImpl(tasks);
        gameMechanic.beginGame();
        Thread.sleep(7000);
        gameMechanic.stopGame();
    }
}