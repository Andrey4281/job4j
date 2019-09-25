package parser;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DateParserTest {

    @Test
    public void whenParseTodayShouldReturnCorrectResult() {
        DateParser dateParser = new DateParser();
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR, 13);
        calendar.set(Calendar.MINUTE, 46);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long excpectedTime = calendar.getTime().getTime();

        long actualTime = dateParser.parseDate("сегодня, 13:46");

        assertThat(actualTime, is(excpectedTime));
    }

    @Test
    public void whenParseYesterdayShouldReturnCorrectResult() {
        DateParser dateParser = new DateParser();
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR, 13);
        calendar.set(Calendar.MINUTE, 46);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long excpectedTime = calendar.getTime().getTime();

        long actualTime = dateParser.parseDate("вчера, 13:46");

        assertThat(actualTime, is(excpectedTime));
    }

    @Test
    public void whenParseLaterDateShouldReturnCorrectResult() {
        DateParser dateParser = new DateParser();
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, 2018);
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DAY_OF_MONTH, 24);
        calendar.set(Calendar.HOUR, 11);
        calendar.set(Calendar.MINUTE, 8);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long excpectedTime = calendar.getTime().getTime();

        long actualTime = dateParser.parseDate("24 дек 18, 11:08");

        assertThat(actualTime, is(excpectedTime));
    }
}