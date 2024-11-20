package net.bomee.util;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.CRC32;

/**
 * Hash/Hmac 工具类
 *
 * @author bomee shiaupo@qq.com
 */
public final class Hashes {

    private Hashes() {
    }

    /**
     * CRC32 Checksum
     *
     * @param bytes 输入字节流
     * @return Checksum
     */
    public static long crc32(byte[] bytes) {
        CRC32 crc32 = new CRC32();
        crc32.update(bytes, 0, bytes.length);
        return crc32.getValue();
    }

    /**
     * MD5
     *
     * @param bytes 原始字节数组
     * @return 字节数组
     */
    public static byte[] md5(byte[] bytes) {
        return hash(bytes, "MD5");
    }

    /**
     * SHA-1
     *
     * @param bytes 原始字节数组
     * @return 字节数组
     */
    public static byte[] sha1(byte[] bytes) {
        return hash(bytes, "SHA-1");
    }

    /**
     * SHA-256
     *
     * @param bytes 原始字节数组
     * @return 字节数组
     */
    public static byte[] sha256(byte[] bytes) {
        return hash(bytes, "SHA-256");
    }

    /**
     * SHA-512
     *
     * @param bytes 原始字节数组
     * @return 字节数组
     */
    public static byte[] sha512(byte[] bytes) {
        return hash(bytes, "SHA-512");
    }

    /**
     * 使用HmacMD5计算摘要
     *
     * @param bytes 原始字节数组
     * @param key   密钥
     * @return 字节数组
     */
    public static byte[] hmacMD5(byte[] bytes, byte[] key) {
        return hmac(bytes, "HmacMD5", key);
    }

    /**
     * 使用HmacMD5计算摘要返回十六进制字符串
     *
     * @param str 原始字符串
     * @param key 密钥
     * @return 十六进制摘要
     */
    public static String hmacMD5(String str, String key) {
        return Bytes.byteToHex(hmacMD5(str.getBytes(), key.getBytes()));
    }

    /**
     * 使用HmacSHA1计算摘要
     *
     * @param bytes 原始字节数组
     * @param key   密钥
     * @return 字节数组
     */
    public static byte[] hmacSha1(byte[] bytes, byte[] key) {
        return hmac(bytes, "HmacSHA1", key);
    }

    /**
     * 使用HmacSHA1计算摘要返回十六进制字符串
     *
     * @param str 原始字符串
     * @param key 密钥
     * @return 十六进制摘要
     */
    public static String hmacSha1(String str, String key) {
        return Bytes.byteToHex(hmacSha1(str.getBytes(), key.getBytes()));
    }

    /**
     * 使用HmacSHA256计算摘要
     *
     * @param bytes 原始字节数组
     * @param key   密钥
     * @return 字节数组
     */
    public static byte[] hmacSha256(byte[] bytes, byte[] key) {
        return hmac(bytes, "HmacSHA256", key);
    }

    /**
     * 使用HmacSHA256计算摘要返回十六进制字符串
     *
     * @param str 原始字符串
     * @param key 密钥
     * @return 十六进制摘要
     */
    public static String hmacSha256(String str, String key) {
        return Bytes.byteToHex(hmacSha256(str.getBytes(), key.getBytes()));
    }

    /**
     * 使用HmacSHA512计算摘要
     *
     * @param bytes 原始字节数组
     * @param key   密钥
     * @return 字节数组
     */
    public static byte[] hmacSha512(byte[] bytes, byte[] key) {
        return hmac(bytes, "HmacSHA512", key);
    }

    /**
     * 使用HmacSHA512计算摘要返回十六进制字符串
     *
     * @param str 原始字符串
     * @param key 密钥
     * @return 十六进制摘要
     */
    public static String hmacSha512(String str, String key) {
        return Bytes.byteToHex(hmacSha512(str.getBytes(), key.getBytes()));
    }

    private static byte[] hash(byte[] input, String algorithm) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance(algorithm);
            return messageDigest.digest(input);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] hmac(byte[] input, String algorithm, byte[] key) {
        try {
            SecretKey secretKey = new SecretKeySpec(key, algorithm);
            Mac mac = Mac.getInstance(algorithm);
            mac.init(secretKey);
            return mac.doFinal(input);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
