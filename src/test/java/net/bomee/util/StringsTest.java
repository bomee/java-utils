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

    @Test
    public void testMask() {
        assertEquals("123****9012", Strings.mask("123456789012", 3, 4));
        assertEquals("****9012", Strings.mask("123456789012", 0, 4));
        assertEquals("123****", Strings.mask("123456789012", 3, 0));
        assertEquals("****", Strings.mask("123456789012", 0, 0));

        assertEquals("123****8901", Strings.mask("12345678901", 3, 4));

        assertEquals("123****890", Strings.mask("1234567890", 3, 4));
        assertEquals("123****89", Strings.mask("123456789", 3, 4));
        assertEquals("123****8", Strings.mask("12345678", 3, 4));
        assertEquals("12****7", Strings.mask("1234567", 3, 4));
        assertEquals("1****6", Strings.mask("123456", 3, 4));
        assertEquals("**", Strings.mask("12", 3, 4));
        assertEquals("*", Strings.mask("1", 3, 4));
    }

    @Test
    public void testRandom() {
        assertEquals(6, Strings.random(6).length());
    }
}