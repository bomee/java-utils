package net.bomee.util;

/**
 * 常用前置条件判断
 *
 * @author bomee yuxiaobo@23mofang.com
 */
public class Predicates {

    /**
     * null对象检查
     *
     * @param object Object
     * @param <T>    Object Type
     * @return not null Object
     * @throws NullPointerException object == null
     */
    public static <T> T checkNotNull(T object) {
        if (object == null) {
            throw new NullPointerException("object must not be null.");
        }
        return object;
    }

    /**
     * 范围检查
     *
     * @param value 被检查的值
     * @param min   最小值（包含）
     * @param max   最大值（排除）
     * @return 是否在范围内
     */
    public static boolean checkRange(int value, int min, int max) {
        return value >= min && value < max;
    }

    /**
     * 范围检查，不在范围抛出指定错误消息的异常
     *
     * @param value        被检查的值
     * @param min          最小值（包含）
     * @param max          最大值（排除）
     * @param errorMessage 错误消息
     * @throws IllegalArgumentException 不在范围内则抛出异常
     */
    public static void checkRange(int value, int min, int max, String errorMessage) {
        if (value < min || value >= max) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    /**
     * 检查条件是否满足，不满足则抛出指定错误消息的异常
     *
     * @param condition    条件
     * @param errorMessage 错误消息
     * @throws IllegalStateException 不满足条件则抛出异常
     */
    public static void checkCondition(boolean condition, String errorMessage) {
        if (!condition) {
            throw new IllegalStateException(errorMessage);
        }
    }
}
