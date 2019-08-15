import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class RobotTest {

    @Test
    public void optimalWay() {
        Robot robot = new Robot();
        int[][] board = new int[][] {{1, 2, 3}, {1, 3, 6}, {1, 1, 5}};

        int costOfPath = robot.optimalWay(board, 0, 0, 2, 2);

        assertThat(costOfPath, is(4));
    }
}