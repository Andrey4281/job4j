package list;

import list.Node;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class NodeTest {
    private Node<Integer> firstWithoutCycle;
    private Node<Integer> firstWithCycle;

    @Before
    public void setLists() {
        firstWithoutCycle = new Node<>(1);
        Node<Integer> twoWithoutCycle = new Node<>(2);
        Node<Integer> threeWithoutCycle = new Node<>(3);
        Node<Integer> fourWithoutCycle = new Node<>(4);
        firstWithoutCycle.next = twoWithoutCycle;
        twoWithoutCycle.next = threeWithoutCycle;
        threeWithoutCycle.next = fourWithoutCycle;

        firstWithCycle = new Node<>(1);
        Node<Integer> twoWithCycle = new Node<>(2);
        Node<Integer> threeWithCycle = new Node<>(3);
        Node<Integer> fourWithCycle = new Node<>(4);
        firstWithCycle.next = twoWithCycle;
        twoWithCycle.next = threeWithCycle;
        threeWithCycle.next = fourWithCycle;
        fourWithCycle.next = firstWithCycle;
    }

    @Test
    public void hasCycle() {
        assertThat(firstWithoutCycle.hasCycle(firstWithoutCycle), is(false));
        assertThat(firstWithCycle.hasCycle(firstWithCycle), is(true));
    }
}