import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ArrayBasedContainerTest {
    private SimpleContainer<Integer> container;
    @Before
    public void setContainer() {
        container = new ArrayBasedContainer<>(2);
        container.add(1);
        container.add(2);
    }

    @Test
    public void sequentialAddMethodInvocation() {
        container.add(3);
        container.add(4);
        assertThat(container.get(0), is(1));
        assertThat(container.get(1), is(2));
        assertThat(container.get(2), is(3));
        assertThat(container.get(3), is(4));
    }

}