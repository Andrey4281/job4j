package parser;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for convertion
 * of time publishing of some
 * vacancy in site to milisecond from 1.01.1970
 * @author asemenov
 * @since 25.09.2018
 * @version 1
 */
public class DateParser {
    private final Map<String, Integer> monthToInteger;

    public DateParser() {
        monthToInteger = new HashMap<>(13, 1);
        monthToInteger.put("янв", 0);
        monthToInteger.put("фев", 1);
        monthToInteger.put("мар", 2);
        monthToInteger.put("апр", 3);
        monthToInteger.put("май", 4);
        monthToInteger.put("июн", 5);
        monthToInteger.put("июл", 6);
        monthToInteger.put("авг", 7);
        monthToInteger.put("сен", 8);
        monthToInteger.put("окт", 9);
        monthToInteger.put("ноя", 10);
        monthToInteger.put("дек", 11);
    }


    public long parseDate(String date) {
        long res = 0;
        if (date.contains("сегодня")) {
            res = parseToday(date);
        } else if (date.contains("вчера")) {
            res = parseYesterday(date);
        } else {
            res = parseLaterDate(date);
        }
        return res;
    }

    private long parseToday(String date) {
        String[] parts = date.split("[\\s:]");
        int hours = Integer.parseInt(parts[parts.length - 2]);
        int minute = Integer.parseInt(parts[parts.length - 1]);

        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR, hours);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime().getTime();
    }

    private long parseYesterday(String date) {
        String[] parts = date.split("[\\s:]");
        int hours = Integer.parseInt(parts[parts.length - 2]);
        int minute = Integer.parseInt(parts[parts.length - 1]);

        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR, hours);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime().getTime();
    }

    private long parseLaterDate(String date) {
        String[] parts = date.split("[\\s:,]");
        int year = Integer.parseInt(parts[2]);
        int month = monthToInteger.get(parts[1]);
        int day = Integer.parseInt(parts[0]);
        int hours = Integer.parseInt(parts[parts.length - 2]);
        int minute = Integer.parseInt(parts[parts.length - 1]);

        Calendar calendar = new GregorianCalendar();
        year = calendar.get(Calendar.YEAR) - (calendar.get(Calendar.YEAR) % 100) + year;
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR, hours);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime().getTime();
    }
}
