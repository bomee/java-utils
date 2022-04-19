package net.bomee.util;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

public class IOsTest {

    @Test
    public void readToByteArray() throws IOException {
        byte[] bytes = "I'm bomee.".getBytes(StandardCharsets.UTF_8);
        assertArrayEquals(bytes, IOs.readToByteArray(new ByteArrayInputStream(bytes)));
    }

    @Test
    public void readToUTF8String() throws IOException {
        assertEquals("I'm bomee.", IOs.readToUTF8String(
                new ByteArrayInputStream("I'm bomee.".getBytes(StandardCharsets.UTF_8))));
    }

    @Test
    public void readToString() throws IOException {
        assertEquals("I'm bomee.", IOs.readToString(
                new ByteArrayInputStream("I'm bomee.".getBytes(StandardCharsets.UTF_8)),
                StandardCharsets.UTF_8.name()
        ));
    }
}