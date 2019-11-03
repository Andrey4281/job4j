package list;

import list.SimpleStack;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleStackTest {
    private SimpleStack<Integer> stack;
    @Before
    public void setStack() {
        stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
    }

    @Test
    public void sequentialOfPushAndPollMethodsInvocations() {
        assertThat(stack.poll(), is(3));
        assertThat(stack.poll(), is(2));
        assertThat(stack.poll(), is(1));
        assertEquals(stack.poll(), null);
    }
}