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
        Board board = new BoardImpl(5, 5, map);
        Runnable player = new Player(new Cell(0, 0), board);
        List<Runnable> tasks = new LinkedList<>();
        tasks.add(player);
        GameMechanic gameMechanic = new GameMechanicImpl(tasks);
        gameMechanic.beginGame();
        Thread.sleep(7000);
        gameMechanic.stopGame();
    }
}