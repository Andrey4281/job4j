package simplearray;

import org.junit.Before;
import org.junit.Test;
import simplearray.SimpleArray;
import simplearray.SimpleArrayIterator;

import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleArrayIteratorTest {
    private SimpleArrayIterator<Integer> iterator;
    @Before
    public void setIterator() {
        SimpleArray<Integer> array = new SimpleArray<>(3);
        array.add(1);
        array.add(2);
        array.add(3);
        iterator = new SimpleArrayIterator<>(array);
    }

    @Test
    public void sequentialHasNextNextInvocation() {
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void sequentialNextInvocationThrowNoSuchElementException() {
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
    }
}