import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BinaryTreeTest {
    private BinaryTree<String, Integer> treeOne;
    private BinaryTree<Integer, String> treeTwo;

    @Before
    public void setTree() {
        treeOne = new BinaryTree<>();
        treeOne.insert("One", 1);
        treeOne.insert("Two", 2);
        treeOne.insert("Three", 3);
        treeOne.insert("Four", 4);

        treeTwo = new BinaryTree<>();
        treeTwo.insert(5, "five");
        treeTwo.insert(4, "four");
        treeTwo.insert(7, "seven");
        treeTwo.insert(2, "two");
        treeTwo.insert(3, "three");
        treeTwo.insert(6, "six");
        treeTwo.insert(8, "eight");
        treeTwo.insert(1, "one");
    }

    @Test
    public void sequentialOfInsertAndFindMethodsInvocations() {
        assertThat(treeOne.find("One"), is(1));
        assertThat(treeOne.find("Two"), is(2));
        assertThat(treeOne.find("Three"), is(3));
        assertThat(treeOne.find("Four"), is(4));
        assertEquals(treeOne.find("Five"), null);
    }

    @Test
    public void sequentialOfDeleteAndInsertAndNextAndHasNextMethodsInvocations() {
        assertThat(treeTwo.delete(2), is(true));
        assertThat(treeTwo.delete(7), is(true));
        assertThat(treeTwo.delete(5), is(true));
        assertThat(treeTwo.delete(10), is(false));
        assertThat(treeTwo.insert(8, "nine"), is(false));

        Iterator<Pair<Integer, String>> iterator = treeTwo.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next().getValue(), is("six"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next().getValue(), is("four"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next().getValue(), is("nine"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next().getValue(), is("three"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next().getValue(), is("one"));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void invocationOfNextMethodShouldThrowNoSuchElementException() {
        Iterator<Pair<Integer, String>> iterator = treeTwo.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        iterator.next();
    }
}