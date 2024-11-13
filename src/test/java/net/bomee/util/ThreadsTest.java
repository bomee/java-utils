package net.bomee.util;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ThreadsTest {

    @Test
    public void runAsync() {
        Threads.runAsync(() -> {
            byte[] bytes = "abc".getBytes(StandardCharsets.UTF_8);
            System.out.println(Arrays.toString(bytes));
        });
    }

    @Test
    public void runAsyncIgnoreException() {
        Threads.runAsyncIgnoreException(() -> {
            byte[] bytes = "abc".getBytes(StandardCharsets.UTF_8);
            System.out.println(Arrays.toString(bytes));
        });
    }

    @Test
    public void runAsyncAfter() throws InterruptedException {
        long delayMS = 10 * 1000;
        final AtomicLong execTime = new AtomicLong(0);
        long now = System.currentTimeMillis();

        CountDownLatch latch = new CountDownLatch(1);

        Threads.runAsyncAfter(() -> {
            execTime.set(System.currentTimeMillis());
            latch.countDown();
        }, delayMS);
        assertEquals(0, execTime.get());

        latch.await();
        assertTrue(execTime.get() - now >= delayMS);
    }
}
