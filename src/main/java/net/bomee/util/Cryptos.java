package net.bomee.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 加解密工具类
 *
 * @author bomee shiaupo@qq.com
 */
public final class Cryptos {
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
}
