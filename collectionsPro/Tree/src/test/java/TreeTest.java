import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TreeTest {
    private Tree<Integer> tree;
    private Tree<Integer> binaryTree;
    private Tree<String> treeOfString;

    @Before
    public void setTrees() {
        tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);

        treeOfString = new Tree<>("One");
        treeOfString.add("One", "Two");
        treeOfString.add("One", "Three");
        treeOfString.add("One", "Four");
        treeOfString.add("Four", "Five");
        treeOfString.add("Five", "Six");

        binaryTree = new Tree<>(1);
        binaryTree.add(1, 2);
        binaryTree.add(1, 3);
        binaryTree.add(2, 4);
        binaryTree.add(2, 6);
        binaryTree.add(3, 7);
    }

    @Test
    public void when6ElFindLastThen6() {
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void sequentialOfHasNextAndNextMethodsInvocations() {
        Iterator<String> iterator = treeOfString.iterator();

        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("One"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("Two"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("Three"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("Four"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("Five"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("Six"));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void isBinary() {
        assertThat(tree.isBinary(), is(false));
        assertThat(binaryTree.isBinary(), is(true));
    }

}