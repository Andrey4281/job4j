import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TreeBasedHashMapTest {
    private TreeBasedHashMap<Integer, String> hashMap;

    @Before
    public void setHashMap() {
        hashMap = new TreeBasedHashMap<>(2);
        hashMap.insert(1, "one");
        hashMap.insert(2, "two");
        hashMap.insert(3, "three");
        hashMap.insert(4, "four");
        hashMap.insert(5, "five");
        hashMap.insert(3, "Andrey");
    }

    @Test
    public void sequentialOfAddAndGetMethodInvocation() {
        assertThat(hashMap.get(1), is("one"));
        assertThat(hashMap.get(2), is("two"));
        assertThat(hashMap.get(3), is("Andrey"));
        assertThat(hashMap.get(4), is("four"));
        assertThat(hashMap.get(5), is("five"));
    }

    @Test
    public void sequentialOfDeleteAndGetMethods() {
        assertThat(hashMap.delete(1), is(true));
        assertThat(hashMap.delete(2), is(true));
        assertThat(hashMap.delete(10), is(false));
        assertThat(hashMap.delete(2), is(false));
        assertThat(hashMap.get(3), is("Andrey"));
        assertThat(hashMap.get(4), is("four"));
    }

    @Test
    public void sequentialOfNextAndHasNextMethodsInvocations() {
        Iterator<Pair<Integer, String>> iterator = hashMap.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next().getValue(), is("four"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next().getValue(), is("one"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next().getValue(), is("five"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next().getValue(), is("two"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next().getValue(), is("Andrey"));
        assertThat(iterator.hasNext(), is(false));
    }

}