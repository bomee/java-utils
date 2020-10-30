package net.bomee.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringsTest {

    @Test
    public void isEmpty() {
        assertTrue(Strings.isEmpty(""));
        assertTrue(Strings.isEmpty(null));
        assertFalse(Strings.isEmpty("a"));
    }

    @Test
    public void isNotEmpty() {
        assertTrue(Strings.isNotEmpty("a"));
        assertFalse(Strings.isNotEmpty(""));
    }

    @Test
    public void isNullOrDefault() {
        assertEquals("a", Strings.isNullOrDefault(null, "a"));
    }

    @Test
    public void testToString() {
        assertEquals("", Strings.toString(null));
        assertEquals("a", Strings.toString("a"));
        assertEquals("1", Strings.toString(1));
        assertEquals("true", Strings.toString(Boolean.TRUE));
        assertEquals("1.0", Strings.toString(1.0));
    }
}