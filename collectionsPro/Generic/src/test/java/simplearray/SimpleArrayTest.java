package simplearray;

import org.junit.Before;
import org.junit.Test;
import simplearray.DemensionOfExceedException;
import simplearray.SimpleArray;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleArrayTest {
    private SimpleArray<Integer> testArray;

    @Before
    public void setTestArray() {
        testArray = new SimpleArray<>(5);
    }

    @Test
    public void sequentialOfAddMethodInvocation() {
        testArray.add(1);
        testArray.add(2);
        testArray.add(3);
        Iterator<Integer> iterator = testArray.iterator();
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(3));
    }

    @Test(expected = DemensionOfExceedException.class)
    public void invocationOfAddMethodShouldThrowDemensionOfExceedException() {
        testArray.add(1);
        testArray.add(2);
        testArray.add(3);
        testArray.add(4);
        testArray.add(5);
        testArray.add(6);
    }

    @Test
    public void invocationOfSetMethod() {
        testArray.add(2);
        testArray.set(0, 4);
        assertThat(testArray.get(0), is(4));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void invocationOfSetMethodShouldThrowIndexOutOfBoundsException() {
        testArray.add(2);
        testArray.set(2, 5);
    }

    @Test
    public void invocationOfGetMethod() {
        testArray.add(1);
        assertThat(testArray.get(0), is(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void invocationOfGetMethodShouldThrowIndexOutOfBoundsException() {
        testArray.add(2);
        testArray.get(1);
    }

    @Test
    public void sequentialRemoveMethodInvocation() {
        testArray.add(1);
        testArray.add(2);
        testArray.add(3);
        testArray.add(4);
        testArray.add(5);
        testArray.remove(1);
        testArray.remove(2);
        Iterator<Integer> iterator = testArray.iterator();
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.next(), is(5));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void invocationOfRemoveMethodShouldThrowIndexOutOfBoundsException() {
        testArray.add(2);
        testArray.remove(1);
    }
}