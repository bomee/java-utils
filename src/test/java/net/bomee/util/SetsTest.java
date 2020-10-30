package net.bomee.util;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class SetsTest {

    @Test
    public void of() {
        Set<String> set = Sets.of("1", "2", "3", "4");
        assertEquals(4, set.size());
    }

    @Test
    public void isEmpty() {
        assertFalse(Sets.isEmpty(Sets.of(0)));
        assertTrue(Sets.isEmpty(null));
    }
}