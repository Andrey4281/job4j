import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ScriptStackImplTest {

    @Test
    public void load() {
        Script script = new ScriptStackImpl();
        Map<Integer, List<Integer>> ds = new HashMap<>();
        ds.put(1, Arrays.asList(2, 3));
        ds.put(2, Arrays.asList(4));
        ds.put(3, Arrays.asList(4, 5));
        ds.put(4, Arrays.asList());
        ds.put(5, Arrays.asList());

        List<Integer> res = script.load(ds, 1);
        Iterator<Integer> iterator = res.iterator();

        assertThat(iterator.next(), is(4));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(5));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(false));
    }
}