package net.bomee.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Formatter;

/**
 * 字节码常用操作集合
 *
 * @author bomee shiaupo@qq.com
 */
public final class Bytes {

    /**
     * 字节数组转16进制字符串
     *
     * @param bytes 字节数组
     * @return 16进制字符串
     */
    public static String byteToHex(byte[] bytes) {
        Formatter formatter = new Formatter(new StringBuilder(bytes.length * 2));
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }

    /**
     * 16进制字符串转字节数组
     *
     * @param hex 16进制字符串
     * @return 字节数组
     */
    public static byte[] hexToByte(String hex) {
        int len = hex.length();
        byte[] bytes = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            bytes[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4) + Character.digit(hex.charAt(i + 1), 16));
        }
        return bytes;
    }

    /**
     * 将字节数组转换为UTF-8字符串
     *
     * @param bytes 字节数组
     * @return UTF-8字符串
     */
    public static String byteToUTF8String(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     * 获取字符串的UTF-8字节数组
     *
     * @param str 字符串
     * @return UTF-8字节数组
     */
    public static byte[] stringToUTF8Byte(String str) {
        return str.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * 将字节数组转换为base64字符串
     *
     * @param bytes 字节数组
     * @return base64字符串
     */
    public static String byteToBase64String(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 将base64字符串转换字节数组
     *
     * @param base64 base64字符串
     * @return 字节数组
     */
    public static byte[] base64StringToByte(String base64) {
        return Base64.getDecoder().decode(base64);
    }
}
