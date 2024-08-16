package net.bomee.util;

import org.junit.Assert;
import org.junit.Test;

public class FunctionsTest {

    @Test
    public void execQuiet() {
        Assert.assertEquals("b", Functions.execQuiet(() -> "b", "a"));
        Assert.assertEquals("a", Functions.execQuiet(() -> {
            throw new IllegalArgumentException();
        }, "a"));

        Assert.assertEquals(Integer.valueOf(0), Functions.execQuiet(() -> Integer.parseInt("a"), 0));
        Assert.assertEquals(Integer.valueOf(1), Functions.execQuiet(() -> Integer.parseInt("1"), 0));
    }

    @Test
    public void execWithRE() {
    }
}
