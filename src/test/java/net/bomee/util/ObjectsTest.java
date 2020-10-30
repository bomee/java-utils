package net.bomee.util;

import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ObjectsTest {

    @Test
    public void isEmpty() {
        assertTrue(Objects.isEmpty(null));
        assertTrue(Objects.isEmpty(""));
        assertTrue(Objects.isEmpty(new ArrayList<>()));
        assertTrue(Objects.isEmpty(new String[0]));

        assertFalse(Objects.isEmpty("a"));
        assertFalse(Objects.isEmpty(Arrays.asList("a", "b")));
        assertFalse(Objects.isEmpty(new String[]{"a"}));
    }

    @Test
    public void copy() {
        A a = new A();
        a.setA("a");
        a.setB(1);
        a.setC(true);
        a.setD("d");
        a.setO(new Object());

        B b = new B();
        Objects.copy(a, b);

        assertEquals(a.getA(), b.getA());
        assertEquals(a.getB(), b.getB());
        assertEquals(a.isC(), b.isC());
        assertEquals("final", b.getD());
        assertEquals(a.getO(), b.getO());
        assertNull(B.f);


        B b2 = new B();
        Objects.copy(a, b, "o");
        assertNull(b2.getO());
    }


    @Getter
    @Setter
    static class Parent {
        private String a;
        private Object o;
        private int b;
        private boolean c;
    }

    @Getter
    @Setter
    static class A extends Parent {
        private String d;
        private static String f = "AF";
    }

    @Getter
    @Setter
    static class B extends Parent {
        private final String d = "final";
        private static String f;
    }
}