package net.bomee.util;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.concurrent.Future;

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
}