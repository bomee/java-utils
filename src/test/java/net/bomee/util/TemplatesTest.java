package net.bomee.util;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class TemplatesTest {

    @Test
    public void test_resolve_success() {
        assertEquals("I'm bomee, from china.",
                Templates.resolve("I'm ${name}, from ${country}.", new HashMap<String, String>() {
                    {
                        put("name", "bomee");
                        put("country", "china");
                    }
                }));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_resolve_no_value() {
        assertEquals("I'm bomee, from china.",
                Templates.resolve("I'm ${name}, from ${country}.", new HashMap<String, String>() {
                    {
                        put("country", "china");
                    }
                }));
    }

    @Test
    public void test_extract_success() {
        assertArrayEquals(new String[]{"name", "country"},
                Templates.extractVariables("I'm ${name}, from ${country}.").toArray());
    }
}