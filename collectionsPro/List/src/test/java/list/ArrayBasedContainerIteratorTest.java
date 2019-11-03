package list;

import list.ArrayBasedContainer;
import list.SimpleContainer;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ArrayBasedContainerIteratorTest {
    private SimpleContainer<Integer> container;

    @Before
    public void setContainer() {
        container = new ArrayBasedContainer<>(2);
        container.add(1);
        container.add(2);
        container.add(3);
        container.add(4);
    }

    @Test
    public void sequentialOfHasNextMethodAndNextInvocations() {
        Iterator<Integer> iterator = container.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.hasNext(), is(false));
    }

}