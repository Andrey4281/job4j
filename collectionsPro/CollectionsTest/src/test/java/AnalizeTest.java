import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AnalizeTest {
    private List<Analize.User> previous;
    private List<Analize.User> current;
    private Analize analize;

    @Before
    public void setLists() {
        analize = new Analize();
        previous = new LinkedList<>();
        current = new LinkedList<>();
        previous.add(new Analize.User(1, "Andrey"));
        previous.add(new Analize.User(2, "Bob"));
        previous.add(new Analize.User(3, "Petr"));
        previous.add(new Analize.User(4, "Olya"));
        previous.add(new Analize.User(5, "Tom"));

        current.add(new Analize.User(1, "Semen"));
        current.add(new Analize.User(2, "Bob"));
        current.add(new Analize.User(3, "Petr"));
        current.add(new Analize.User(6, "Nina"));
        current.add(new Analize.User(7, "John"));
    }

    @Test
    public void diffMethodInvocationForNotNullArguments() {
        Analize.Info info = analize.diff(previous, current);

        assertThat(info.changed, is(1));
        assertThat(info.added, is(2));
        assertThat(info.deleted, is(2));
    }

    @Test
    public void whenOneArgumentsIsNullDiffMethodShouldReturnEmptyInfo() {
        Analize.Info info = analize.diff(null, current);

        assertThat(info.changed, is(0));
        assertThat(info.added, is(0));
        assertThat(info.deleted, is(0));
    }
}