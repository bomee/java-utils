package net.bomee.util;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * String 常用操作集合
 *
 * @author bomee shiaupo@qq.com
 */
public final class Strings {
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

    /**
     * 字符串截取(第一组满足条件）
     *
     * @param src    原字符串
     * @param prefix 截取的前缀开头
     * @param suffix 截取的后缀结尾
     * @return 截取的字符串（不含前后缀），不匹配则返回null
     */
    public static String sub(String src, String prefix, String suffix) {
        if (src == null) {
            return null;
        }
        int startOffset = prefix == null ? 0 : src.indexOf(prefix);
        if (startOffset < 0) {
            return null;
        }

        int endOffset = suffix == null ? src.length() : src.indexOf(suffix, startOffset);

        return endOffset < startOffset
                ? null
                : src.substring(startOffset + (prefix == null ? 0 : prefix.length()), endOffset);
    }

    /**
     * 截取最后一组满足条件的字符串
     *
     * @param src    原字符串
     * @param prefix 截取的前缀开头
     * @param suffix 截取的后缀结尾
     * @return 截取的字符串（不含前后缀），不匹配则返回null
     */
    public static String subLast(String src, String prefix, String suffix) {
        if (src == null) {
            return null;
        }

        int endOffset = suffix == null ? src.length() : src.lastIndexOf(suffix);
        if (endOffset < 0) {
            return null;
        }
        int startOffset = prefix == null ? 0 : src.substring(0, endOffset).lastIndexOf(prefix);

        endOffset = suffix == null ? src.length() : src.indexOf(suffix, startOffset);

        return startOffset < 0 || endOffset < startOffset
                ? null
                : src.substring(startOffset + (prefix == null ? 0 : prefix.length()), endOffset);
    }

    /**
     * 头部填充指定字符（本身已达到长度则不填充）
     *
     * @param str     被填充的字符串
     * @param length  填充后的长度
     * @param padChar 填充字符
     * @return 填充后字符串
     */
    public static String paddingStart(String str, int length, char padChar) {
        return str != null && length > str.length() ? repeat(String.valueOf(padChar), length - str.length()) + str : str;
    }

    /**
     * 尾部填充指定字符（本身已达到长度则不填充）
     *
     * @param str     被填充的字符串
     * @param length  填充后的长度
     * @param padChar 填充字符
     * @return 填充后字符串
     */
    public static String paddingEnd(String str, int length, char padChar) {
        return str != null && length > str.length() ? str + repeat(String.valueOf(padChar), length - str.length()) : str;
    }

    /**
     * 随机字符串
     *
     * @param length 随机长度
     * @return 随机字符串
     */
    public static String random(int length) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        char[] candidate = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] chars = new char[length];
        for (int i = 0; i < length; i++) {
            chars[i] = candidate[random.nextInt(candidate.length)];
        }
        return new String(chars);
    }

    /**
     * 重复字符串
     *
     * @param str   被重复的字符串
     * @param times 重复次数
     * @return 重复字符串 str * times
     */
    public static String repeat(String str, int times) {
        if (times <= 1) {
            return str;
        }
        return Stream.generate(() -> str).limit(times).collect(Collectors.joining());
    }

    /**
     * 为字符串进行打码, *占位数最大为4
     * <pre>
     *     mask("13812341234", 3, 4) -> "138****1234"
     *     mask("123456", 3, 4) -> "1****6"
     *     mask("12345", 3, 4) -> "1***5"
     *     mask("1234", 3, 4) -> "1**4"
     *     mask("123", 3, 4) -> "1*3"
     *     mask("12", 3, 4) -> "**"
     *     mask("1", 3, 4) -> "*"
     * </pre>
     *
     * @param str                原始字符串
     * @param retainPrefixLength 首部保留的长度
     * @param retainSuffixLength 尾部保留的长度
     * @return 打码后的字符串
     */
    public static String mask(String str, int retainPrefixLength, int retainSuffixLength) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        int length = str.length();
        if (length <= 2) {
            return repeat("*", length);
        }
        int maskLength = 4;
        while (retainPrefixLength + maskLength + retainSuffixLength > length) {
            if (retainSuffixLength > 1) {
                retainSuffixLength--;
            } else if (retainPrefixLength > 1) {
                retainPrefixLength--;
            } else if (maskLength > 1) {
                maskLength--;
            } else {
                break;
            }
        }
        return str.substring(0, retainPrefixLength) + repeat("*", maskLength) + str.substring(length - retainSuffixLength, length);
    }
}
