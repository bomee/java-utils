package net.bomee.util;

import java.util.function.Supplier;

/**
 * 函数式便捷操作类
 */
public class Functions {

    @FunctionalInterface
    interface Action {
        void run() throws Exception;
    }

    /**
     * 静默执行，异常时返回默认值，无异常抛出
     *
     * @param supplier     执行的方法
     * @param defaultValue 默认值
     * @param <T>          返回类型
     * @return 执行结果或默认值
     */
    public static <T> T execQuiet(Supplier<T> supplier, T defaultValue) {
        try {
            return supplier.get();
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 静默执行，异常时被忽略，无异常抛出
     *
     * @param action 执行的方法
     */
    public static void execQuiet(Action action) {
        try {
            action.run();
        } catch (Exception ignored) {
        }
    }


    /**
     * 执行方法，异常时抛出RuntimeException
     *
     * @param supplier 执行的方法
     * @param <T>      返回类型
     * @return 执行结果或抛出RuntimeException
     */
    public static <T> T execWithRE(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 执行方法，异常时抛出RuntimeException
     *
     * @param action 执行的方法
     */
    public static void execWithRE(Action action) {
        try {
            action.run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
