package net.bomee.util;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程 相关操作工具方法
 *
 * @author bomee shiaupo@qq.com
 */
public final class Threads {
    /**
     * 异步执行
     *
     * @param runnable Runnable
     * @return Future<Void>
     */
    public static Future<Void> runAsync(Runnable runnable) {
        return CompletableFuture.runAsync(runnable);
    }

    /**
     * 异步执行并忽略异常
     *
     * @param runnable ExceptionRunnable
     * @return Future<Void>
     */
    public static Future<Void> runAsyncIgnoreException(ExceptionRunnable runnable) {
        return CompletableFuture.runAsync(() -> {
            try {
                runnable.run();
            } catch (Exception ignored) {

            }
        });
    }

    /**
     * 创建一个命名化的线程工厂
     *
     * @param prefix 线程前缀
     * @param daemon 是否守护线程
     * @return ThreadFactory
     */
    public static ThreadFactory newNamedThreadFactory(String prefix, boolean daemon) {
        return new NamedThreadFactory(prefix, daemon);
    }

    @FunctionalInterface
    interface ExceptionRunnable {
        void run() throws Exception;
    }

    private static class NamedThreadFactory implements ThreadFactory {
        private final String prefix;
        private final boolean daemon;
        private final ThreadGroup group;
        private final AtomicInteger counter = new AtomicInteger(0);

        private NamedThreadFactory(String prefix, boolean daemon) {
            SecurityManager s = System.getSecurityManager();
            this.group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.prefix = prefix;
            this.daemon = daemon;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(group, r, prefix + counter.incrementAndGet());
            thread.setDaemon(daemon);
            return thread;
        }
    }
}
