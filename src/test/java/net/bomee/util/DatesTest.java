package net.bomee.util;

import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DatesTest {

    final Date testDate = new Date(1542079899000L);


    @Test
    public void of() {
        assertEquals(testDate, Dates.of(2018, 11, 13, 11, 31, 39, 0));
    }

    @Test
    public void format() {
        assertEquals("2018-11-13 11:31:39", Dates.format(testDate, "yyyy-MM-dd HH:mm:ss"));
    }

    @Test
    public void parse() throws ParseException {
        assertEquals(testDate, Dates.parse("2018-11-13 11:31:39", "yyyy-MM-dd HH:mm:ss"));
    }

    @Test
    public void parseQuiet() {
        assertNull(Dates.parseQuiet("", "yyyy-MM-dd HH:mm:ss"));
    }

    @Test
    public void firstDayOfMonth() {
        assertEquals("2018-11-01 00:00:00", Dates.formatDefault(Dates.firstDayOfMonth(testDate)));
    }

    @Test
    public void lastDayOfMonth() {
        assertEquals("2018-11-30 23:59:59", Dates.formatDefault(Dates.lastDayOfMonth(testDate)));
    }

    @Test
    public void firstDayOfWeek() {
        assertEquals("2018-11-12 00:00:00", Dates.formatDefault(Dates.firstDayOfWeek(testDate)));
    }

    @Test
    public void lastDayOfWeek() {
        assertEquals("2018-11-18 23:59:59", Dates.formatDefault(Dates.lastDayOfWeek(testDate)));
    }
}
