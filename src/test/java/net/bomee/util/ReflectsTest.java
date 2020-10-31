package net.bomee.util;

import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Map;

import static org.junit.Assert.*;

public class ReflectsTest {

    @Test
    public void testGetDeclaredField() {
        assertNotNull(Reflects.getDeclaredField(A.class, "a"));
        assertNotNull(Reflects.getDeclaredField(A.class, "p"));
    }

    @Test
    public void getAllDeclaredFields() {
        Map<String, Field> fields = Reflects.getAllDeclaredFields(A.class);
        assertEquals(2, fields.size());
        assertTrue(fields.containsKey("a"));
        assertTrue(fields.containsKey("p"));
    }

    @Getter
    @Setter
    static class Parent {
        private String p;
    }

    @Getter
    @Setter
    static class A extends Parent {
        private String a;
    }
}