package net.bomee.util;

import org.junit.Test;

import static org.junit.Assert.*;

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
}