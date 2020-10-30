package net.bomee.util;

/**
 * String 常用操作集合
 *
 * @author bomee shiaupo@qq.com
 */
public abstract class Strings {
    /**
     * 是否为空字符串
     *
     * @param str 被判断的字符串
     * @return str 为null或者长度为0时返回true，否则返回false
     */
    public static boolean isEmpty(Object str) {
        return str == null || "".equals(str);
    }

    /**
     * 是否不为空字符串
     *
     * @param str 被判断的字符串
     * @return str 为null或者长度为0时返回false，否则返回true
     */
    public static boolean isNotEmpty(Object str) {
        return !isEmpty(str);
    }

    /**
     * 当str为null时返回默认值
     *
     * @param str        被判断的字符串
     * @param defaultStr 默认字符串
     * @return str 不为null时返回str，否则返回defaultStr
     */
    public static String isNullOrDefault(String str, String defaultStr) {
        return str == null ? defaultStr : str;
    }

    /**
     * 安全的将object转化为String
     *
     * @param object 待转化的对象
     * @return 转换后的字符串，注意：null会被转换为""
     */
    public static String toString(Object object) {
        return object == null ? "" : object.toString();
    }
}
