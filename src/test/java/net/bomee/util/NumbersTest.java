package net.bomee.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumbersTest {

    @Test
    public void toInt() {
        assertEquals(123, Numbers.toInt(123L, 0));
        assertEquals(123, Numbers.toInt(123.D, 0));
        assertEquals(123, Numbers.toInt(123.F, 0));
        assertEquals(0, Numbers.toInt(null, 0));
    }

    @Test
    public void toLong() {
        assertEquals(123L, Numbers.toLong(123, 0));
        assertEquals(123L, Numbers.toLong(123.D, 0));
        assertEquals(123L, Numbers.toLong(123.F, 0));
        assertEquals(0L, Numbers.toLong(null, 0));
    }

    @Test
    public void toShort() {
        assertEquals(123L, Numbers.toShort(123, 0));
        assertEquals(123L, Numbers.toShort(123.D, 0));
        assertEquals(123L, Numbers.toShort(123.F, 0));
        assertEquals(0L, Numbers.toShort(null, 0));
    }

    @Test
    public void parseInt() {
        assertEquals(123, Numbers.parseInt(123, 0));
        assertEquals(123, Numbers.parseInt(123.5, 0));
        assertEquals(123, Numbers.parseInt("123", 0));
        assertEquals(0, Numbers.parseInt("abc", 0));
        assertEquals(0, Numbers.parseInt(null, 0));
        assertEquals(0, Numbers.parseInt(true, 0));
    }

    @Test
    public void parseLong() {
        assertEquals(123L, Numbers.parseLong(123, 0));
        assertEquals(123L, Numbers.parseLong(123.5, 0));
        assertEquals(123L, Numbers.parseLong("123", 0));
        assertEquals(0L, Numbers.parseLong("abc", 0));
        assertEquals(0L, Numbers.parseLong(null, 0));
        assertEquals(0L, Numbers.parseLong(true, 0));
    }

    @Test
    public void parseDouble() {
        assertEquals(123, Numbers.parseDouble(123, 0), 0.1);
        assertEquals(123.5, Numbers.parseDouble(123.5, 0), 0.1);
        assertEquals(123.5, Numbers.parseDouble("123.5", 0), 0.1);
        assertEquals(0, Numbers.parseDouble("abc", 0), 0.1);
        assertEquals(0, Numbers.parseDouble(null, 0), 0.1);
        assertEquals(0, Numbers.parseDouble(true, 0), 0.1);
    }
}
