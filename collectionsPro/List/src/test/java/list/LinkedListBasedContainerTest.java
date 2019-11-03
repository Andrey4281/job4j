package list;

import list.LinkedListBasedContainer;
import list.SimpleContainer;
import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class LinkedListBasedContainerTest {
    private SimpleContainer<Integer> container;
    private Iterator<Integer> iterator;
    @Before
    public void setContainer() {
        container = new LinkedListBasedContainer<>();
        container.add(1);
        container.add(2);
        container.add(3);
        container.add(4);
        iterator = container.iterator();
    }

    @Test
    public void sequentialAddMethodInvocation() {
        assertThat(container.get(0), is(1));
        assertThat(container.get(1), is(2));
        assertThat(container.get(2), is(3));
        assertThat(container.get(3), is(4));
    }

    @Test
    public void sequentialNextAndHasNextMethodsOfIteratorInvocation() {
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

    @Test(expected = ConcurrentModificationException.class)
    public void whenInvocationHasNextMethodOfIteratorShouldThrowConcurrentModificationException() {
        container.add(5);
        iterator.hasNext();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenInvocationOfAddMethodShouldThrowIndexOutOfBoundsException() {
        container.get(4);
    }
}