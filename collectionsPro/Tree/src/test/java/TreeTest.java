import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TreeTest {
    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void sequentialOfHasNextAndNextMethodsInvocations() {
        Tree<String> tree = new Tree<>("One");
        tree.add("One", "Two");
        tree.add("One", "Three");
        tree.add("One", "Four");
        tree.add("Four", "Five");
        tree.add("Five", "Six");
        tree.add("Five", "Two");
        Iterator<String> iterator = tree.iterator();

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
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

}