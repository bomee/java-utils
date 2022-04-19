package net.bomee.util;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.zip.CRC32;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Hash/散列，对称加密，非对称加密等安全相关的操作集合
 *
 * @author bomee shiaupo@qq.com
 */
public final class Securities {

    private Securities() {
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
     * @param key   key
     * @return 字节数组
     */
    public static byte[] hmacMD5(byte[] bytes, byte[] key) {
        return hmac(bytes, "HmacMD5", key);
    }

    /**
     * 使用HmacSHA1计算摘要
     *
     * @param bytes 原始字节数组
     * @param key   key
     * @return 字节数组
     */
    public static byte[] hmacSha1(byte[] bytes, byte[] key) {
        return hmac(bytes, "HmacSHA1", key);
    }

    /**
     * 使用HmacSHA256计算摘要
     *
     * @param bytes 原始字节数组
     * @param key   key
     * @return 字节数组
     */
    public static byte[] hmacSha256(byte[] bytes, byte[] key) {
        return hmac(bytes, "HmacSHA256", key);
    }

    /**
     * 使用HmacSHA512计算摘要
     *
     * @param bytes 原始字节数组
     * @param key   key
     * @return 字节数组
     */
    public static byte[] hmacSha512(byte[] bytes, byte[] key) {
        return hmac(bytes, "HmacSHA512", key);
    }

    /**
     * 使用AES对称加密
     *
     * @param bytes 原始字节数组
     * @param key   密钥
     * @return 字节数组
     */
    public static byte[] encryptAES(byte[] bytes, byte[] key) {
        return aes(bytes, key, Cipher.ENCRYPT_MODE);
    }

    /**
     * 使用AES对称解密
     *
     * @param bytes 原始字节数组
     * @param key   密钥
     * @return 字节数组
     */
    public static byte[] decryptAES(byte[] bytes, byte[] key) {
        return aes(bytes, key, Cipher.DECRYPT_MODE);
    }

    /**
     * 使用RSA非对称加密, 基于质数分解
     *
     * @param bytes     原始字节数组
     * @param publicKey 公钥
     * @return 字节数组
     */
    public static byte[] encryptRSA(byte[] bytes, byte[] publicKey) {
        return rsa(bytes, publicKey, Cipher.ENCRYPT_MODE);
    }

    /**
     * 使用RSA非对称解密, 基于质数分解
     *
     * @param bytes      原始字节数组
     * @param privateKey 私钥
     * @return 字节数组
     */
    public static byte[] decryptRSA(byte[] bytes, byte[] privateKey) {
        return rsa(bytes, privateKey, Cipher.DECRYPT_MODE);
    }

    private static byte[] rsa(byte[] bytes, byte[] key, int mode) {
        try {
            Key cipherKey;
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            if (mode == Cipher.ENCRYPT_MODE) {
                X509EncodedKeySpec keySpec = new X509EncodedKeySpec(key);
                cipherKey = keyFactory.generatePublic(keySpec);
            } else {
                PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(key);
                cipherKey = keyFactory.generatePrivate(keySpec);
            }
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(mode, cipherKey);
            return cipher.doFinal(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] aes(byte[] bytes, byte[] key, int mode) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(key);
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(mode, secretKeySpec);
            return cipher.doFinal(bytes);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
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
