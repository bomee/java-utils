package net.bomee.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringsTest {

    @Test
    public void isEmpty() {
        assertTrue(Strings.isEmpty(""));
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

    @Test
    public void sub() {
        assertEquals("b", Strings.sub("abc", "a", "c"));
        assertEquals("", Strings.sub("abc", "a", "b"));

        assertEquals("d", Strings.sub("abcda", "c", "a"));

        assertEquals("a", Strings.sub("abc", null, "b"));
        assertEquals("bc", Strings.sub("abc", "a", null));
        assertEquals("abc", Strings.sub("abc", null, null));
        assertNull(Strings.sub("abc", "a", "d"));
        assertNull(Strings.sub("abc", "d", "c"));

        assertNull(Strings.sub("abc", "c", "a"));
    }

    @Test
    public void subLast() {
        assertEquals("b", Strings.subLast("abc", "a", "c"));
        assertEquals("", Strings.subLast("abc", "a", "b"));
        assertEquals("a", Strings.subLast("abc", null, "b"));
        assertEquals("bc", Strings.subLast("abc", "a", null));
        assertEquals("abc", Strings.subLast("abc", null, null));
        assertNull(Strings.subLast("abc", "a", "d"));
        assertNull(Strings.subLast("abc", "d", "c"));

        assertNull(Strings.subLast("abc", "c", "a"));

        assertEquals("d", Strings.subLast("abcadc", "a", "c"));
        assertEquals("b", Strings.subLast("abcad", "a", "c"));
        assertEquals("b", Strings.subLast("abcdc", "a", "c"));
    }

    @Test
    public void paddingStart() {
        assertEquals("****a", Strings.paddingStart("a", 5, '*'));
        assertEquals("abcde", Strings.paddingStart("abcde", 5, '*'));
        assertNull(Strings.paddingStart(null, 5, '*'));
    }

    @Test
    public void paddingEnd() {
        assertEquals("a****", Strings.paddingEnd("a", 5, '*'));
        assertEquals("abcde", Strings.paddingEnd("abcde", 5, '*'));
        assertNull(Strings.paddingEnd(null, 5, '*'));
    }

    @Test
    public void regexSubstring() {
        assertEquals("bomee", Strings.regexSubstring("I'm (\\w+)\\b", "I'm bomee"));
        assertEquals("18", Strings.regexSubstring(" (\\d+) years", "I am 18 years old."));
    }
}