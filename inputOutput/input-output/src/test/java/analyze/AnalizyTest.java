package analyze;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class AnalizyTest {
    private Analizy analizy;

    @Before
    public void setAnalizy() {
        analizy = new Analizy();
    }

    @Test
    public void whenInputFileOutputShouldReturnUnavailableTime() {
        analizy.unavailable("./../../server.log", "./../../unavailable.csv");
        try (BufferedReader reader = new BufferedReader(new FileReader("./../../unavailable.csv"))) {
             assertThat(reader.readLine(), is("10:57:01;10:59:01"));
             assertThat(reader.readLine(), is("11:01:02;11:02:02"));
             assertEquals(reader.readLine(), null);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}