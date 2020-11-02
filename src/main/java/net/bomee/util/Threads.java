package net.bomee.util;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程 相关操作工具方法
 *
 * @author bomee shiaupo@qq.com
 */
public final class Threads {

    /**
     * A simple lock for lazy create object.
     */
    private static final Object LOCK = new Object();

    /**
     * Global Share ScheduledExecutorService.
     */
    private static volatile ScheduledExecutorService scheduledExecutor;

    /**
     * Singleton Hook Thread.
     */
    private static volatile HookThread hookThread;

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
     * 获取全局的ScheduledExecutorService
     *
     * @return ScheduledExecutorService
     */
    public static ScheduledExecutorService getGlobalScheduledExecutor() {
        if (scheduledExecutor == null) {
            synchronized (LOCK) {
                if (scheduledExecutor == null) {
                    // 可通过Threads.ScheduledExecutor.PoolSize配置调度线程的大小
                    scheduledExecutor = new ScheduledThreadPoolExecutor(Integer.parseInt(System.getProperty("Threads.ScheduledExecutor.PoolSize", "1")));
                    registerShutdownHook(() -> {
                        scheduledExecutor.shutdown();
                    });
                }
            }
        }
        return scheduledExecutor;
    }

    /**
     * 注册ShutdownHook，一个进程实例使用一个Hook Thread，线程内使用串行方式运行，避免多线程环境出现竞争问题
     * JVM Hook 会延迟JVM的关闭，请不要在 Hook 中执行长时间的任务
     *
     * @param runnable Runnable
     */
    public static void registerShutdownHook(Runnable runnable) {
        if (hookThread == null) {
            synchronized (LOCK) {
                if (hookThread == null) {
                    hookThread = new HookThread();
                    Runtime.getRuntime().addShutdownHook(hookThread);
                }
            }
        }
        hookThread.register(runnable);
    }


    /**
     * 异步延迟执行
     *
     * @param runnable     Runnable
     * @param milliseconds 延迟毫秒数
     */
    public static void runAsyncAfter(Runnable runnable, long milliseconds) {
        getGlobalScheduledExecutor().schedule(runnable, milliseconds, TimeUnit.MILLISECONDS);
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

    private static class HookThread extends Thread {

        private final Set<Runnable> runnableSet = new HashSet<>();

        {
            setName("Thread-Singleton-Hook");
        }

        private void register(Runnable runnable) {
            runnableSet.add(runnable);
        }

        @Override
        public void run() {
            runnableSet.forEach(runnable -> {
                try {
                    runnable.run();
                } catch (Throwable ignored) {

                }
            });
        }
    }
}
