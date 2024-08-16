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

    /**
     * 安全的获取Number的值，如果为null则返回defaultValue
     *
     * @param number       Number
     * @param defaultValue 默认值
     * @return 如果为null则返回defaultValue
     */
    public static double toDouble(Number number, double defaultValue) {
        return number == null ? (short) defaultValue : number.doubleValue();
    }

    /**
     * 安静的转换字符串为int
     *
     * @param value        被转换的对象
     * @param defaultValue 转换失败的默认值
     * @return int
     */
    public static int parseInt(Object value, int defaultValue) {
        try {
            if (value instanceof Number) {
                return toInt((Number) value, defaultValue);
            }
            return Integer.parseInt(value.toString());
        } catch (NullPointerException | NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 安静的转换字符串为long
     *
     * @param value        被转换的对象
     * @param defaultValue 转换失败的默认值
     * @return long
     */
    public static long parseLong(Object value, long defaultValue) {
        try {
            if (value instanceof Number) {
                return toLong((Number) value, defaultValue);
            }
            return Long.parseLong(value.toString());
        } catch (NullPointerException | NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 安静的转换字符串为double
     *
     * @param value        被转换的对象
     * @param defaultValue 转换失败的默认值
     * @return double
     */
    public static double parseDouble(Object value, double defaultValue) {
        try {
            if (value instanceof Number) {
                return toDouble((Number) value, defaultValue);
            }
            return Double.parseDouble(value.toString());
        } catch (NullPointerException | NumberFormatException e) {
            return defaultValue;
        }
    }
}
