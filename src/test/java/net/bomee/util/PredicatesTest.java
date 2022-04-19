package net.bomee.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class PredicatesTest {

    @Test
    public void checkNotNull() {
        Object obj = new Object();
        assertEquals(obj, Predicates.checkNotNull(obj));
    }

    @Test(expected = NullPointerException.class)
    public void checkNullException() {
        Predicates.checkNotNull(null);
    }

    @Test
    public void checkRange() {
        assertTrue(Predicates.checkRange(10, 1, 11));
        assertFalse(Predicates.checkRange(10, 1, 9));
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkRangeException() {
        Predicates.checkRange(10, 1, 9, "value must in range [1, 9]");
    }

    @Test(expected = IllegalStateException.class)
    public void checkCondition() {
        Predicates.checkCondition(false, "value must be true.");
    }
}