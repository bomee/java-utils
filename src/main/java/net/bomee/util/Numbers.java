package net.bomee.util;

/**
 * Number累 常用操作集合
 *
 * @author bomee shiaupo@qq.com
 */
public final class Numbers {
    private Numbers() {
    }

    /**
     * 安全的获取Number的值，如果为null则返回defaultValue
     *
     * @param number       Number
     * @param defaultValue 默认值
     * @return 如果为null则返回defaultValue
     */
    public static int toInt(Number number, int defaultValue) {
        return number == null ? defaultValue : number.intValue();
    }

    /**
     * 安全的获取Number的值，如果为null则返回defaultValue
     *
     * @param number       Number
     * @param defaultValue 默认值
     * @return 如果为null则返回defaultValue
     */
    public static long toLong(Number number, long defaultValue) {
        return number == null ? defaultValue : number.longValue();
    }

    /**
     * 安全的获取Number的值，如果为null则返回defaultValue
     *
     * @param number       Number
     * @param defaultValue 默认值
     * @return 如果为null则返回defaultValue
     */
    public static short toShort(Number number, int defaultValue) {
        return number == null ? (short) defaultValue : number.shortValue();
    }
}
