package seafight;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class FieldForBattleTest {
    private static final String LN = System.getProperty("line.separator");

    @Test
    public void whenMarkSomeCellsOfFieldShouldGetFieldInConsole() {
        Field field = null;
        field.inicialize(2);
        field.markCellOfField(0, 0, '*');
        field.markCellOfField(0, 1, 'i');
        field.markCellOfField(1, 0, 'k');
        ByteArrayOutputStream actual = null;
        String expected = Joiner.on(LN).join("* i", "k _", "");

        assertThat(actual.toString(), is(expected));
    }
}