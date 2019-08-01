import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {
    private SimpleSet<Integer> set;
    private SimpleSet<Integer> setContainedNull;

    @Before
    public void setSet() {
        set = new SimpleSet<>(2);
        set.add(1);
        set.add(2);
        set.add(2);
        set.add(3);
        set.add(3);

        setContainedNull = new SimpleSet<>(5);
        setContainedNull.add(1);
        setContainedNull.add(null);
        setContainedNull.add(1);
        setContainedNull.add(2);
        setContainedNull.add(null);
    }

    @Test
    public void sequentialOfHasNextMethodAndNextInvocations() {
        Iterator<Integer> iterator = set.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void sequentialOfHasNextAndNextMethodInvocationForSetContainedNull() {
        Iterator<Integer> iterator = setContainedNull.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertEquals(iterator.next(), null);
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(false));
    }
}