import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Robot {
    private static final class PathsToCell {
        private int xCell;
        private int yCell;
        private Integer costOfPath;
        private boolean isBuilt;

        public PathsToCell(int xCell, int yCell, int costOfPath) {
            this.xCell = xCell;
            this.yCell = yCell;
            this.costOfPath = costOfPath;
            this.isBuilt = false;
        }
    }

    public int optimalWay(int[][] board, int sx, int sy, int fx, int fy) {
        PathsToCell[][] tableOfPaths = buildTableOfPaths(board.length, board[0].length);
        Queue<PathsToCell> queue = new PriorityQueue<>(
                (PathsToCell one, PathsToCell two)
                        -> { return one.costOfPath.compareTo(two.costOfPath); });
        queue.offer(new PathsToCell(sx, sy, 0));
        while (!queue.isEmpty() && !tableOfPaths[fx][fy].isBuilt) {
            PathsToCell current = queue.poll();
            if (!tableOfPaths[current.xCell][current.yCell].isBuilt) {
                current.isBuilt = true;
                tableOfPaths[current.xCell][current.yCell] = current;
            }
            addCellsToQueue(current, queue, tableOfPaths, board);
        }
        return tableOfPaths[fx][fy].costOfPath;
    }

    private void addCellsToQueue(PathsToCell current, Queue<PathsToCell> queue,
                                         PathsToCell[][] tableOfPaths, int[][] board) {
        List<PathsToCell> paths = buildPathsFromCurrent(current, board);
        for (PathsToCell path: paths) {
            if (!tableOfPaths[path.xCell][path.yCell].isBuilt
            && tableOfPaths[path.xCell][path.yCell].costOfPath > path.costOfPath) {
                tableOfPaths[path.xCell][path.yCell] = path;
                queue.offer(path);
            }
        }
    }

    private ArrayList<PathsToCell> buildPathsFromCurrent(PathsToCell current, int[][] board) {
        int x = current.xCell, y = current.yCell;
        int downX = current.xCell + 1;
        int rightY = current.yCell + 1;
        int topX = current.xCell - 1;
        int sizeX = board.length, sizeY = board[0].length;
        ArrayList<PathsToCell> paths = new ArrayList<>(3);
        if (downX < sizeX) {
            paths.add(new PathsToCell(downX, y, current.costOfPath
                    + Math.abs(board[x][y] - board[downX][y])));
        }
        if (rightY < sizeY) {
            paths.add(new PathsToCell(x, rightY, current.costOfPath
                    + Math.abs(board[x][y] - board[x][rightY])));
        }
        if (topX >= 0) {
            paths.add(new PathsToCell(topX, y, current.costOfPath
                    + Math.abs(board[x][y] - board[topX][y])));
        }
        return paths;
    }

    private PathsToCell[][] buildTableOfPaths(int sizeX, int sizeY) {
        PathsToCell[][] tableOfPaths = new PathsToCell[sizeX][sizeY];
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                tableOfPaths[i][j] = new PathsToCell(i, j, Integer.MAX_VALUE);
            }
        }
        return tableOfPaths;
    }
}
