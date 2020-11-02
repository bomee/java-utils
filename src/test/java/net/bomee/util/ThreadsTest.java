package net.bomee.util;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ThreadsTest {

    @Test
    public void runAsync() {
        Threads.runAsync(() -> {
            try {
                byte[] bytes = "abc".getBytes("UTF-8");
                System.out.println(Arrays.toString(bytes));
            } catch (UnsupportedEncodingException ignored) {

            }
        });
    }

    @Test
    public void runAsyncIgnoreException() {
        Threads.runAsyncIgnoreException(() -> {
            byte[] bytes = "abc".getBytes("UTF-8");
            System.out.println(Arrays.toString(bytes));
        });
    }

    @Test
    public void runAsyncAfter() throws InterruptedException {
        final AtomicLong execTime = new AtomicLong(0);
        long now = System.currentTimeMillis();
        Threads.runAsyncAfter(() -> {
            execTime.set(System.currentTimeMillis());
        }, 2000);
        assertEquals(0, execTime.get());

        Thread.sleep(2500);
        assertTrue(execTime.get() - now >= 2000);
    }
}