package net.bomee.util;

import org.junit.Test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

public class SecuritiesTest {

    @Test
    public void md5() {
        byte[] bytes = Securities.md5("Securities.md5".getBytes());
        assertEquals("7e6a7b304ba09685378984c2f2271e3d", Bytes.byteToHex(bytes));
    }

    @Test
    public void sha1() {
        byte[] bytes = Securities.sha1("Securities.sha1".getBytes());
        assertEquals("f7233a6576fac8a283ea56f38fdf01054c7fd958", Bytes.byteToHex(bytes));
    }

    @Test
    public void sha256() {
        byte[] bytes = Securities.sha256("Securities.sha256".getBytes());
        assertEquals("5c259158221dc11dc9b5c65e6f5a6b36979a4e11d851a51ad1fe4e59c98de261", Bytes.byteToHex(bytes));
    }

    @Test
    public void sha512() {
        byte[] bytes = Securities.sha512("Securities.sha512".getBytes());
        assertEquals("158a1f52a55b766ceae754cb666772737fd4327a9257a264490b8b2d2d41c365962717154d2fd40b63f75e018bc9469381890d48327643c72d29033f853fce9e", Bytes.byteToHex(bytes));
    }

    @Test
    public void hmacMD5() {
        byte[] bytes = Securities.hmacMD5("Securities.hmacMD5".getBytes(), "Securities.hmacMD5".getBytes());
        assertEquals("bab9b32b01f8e0665a4a5aeb20b22ddb", Bytes.byteToHex(bytes));
    }

    @Test
    public void hmacSha1() {
        byte[] bytes = Securities.hmacSha1("Securities.hmacSha1".getBytes(), "Securities.hmacSha1".getBytes());
        assertEquals("1801a34930f75339f053da290e3a41e88fcf82b0", Bytes.byteToHex(bytes));
    }

    @Test
    public void hmacSha256() {
        byte[] bytes = Securities.hmacSha256("Securities.hmacSha256".getBytes(), "Securities.hmacSha256".getBytes());
        assertEquals("875f5d4c888f091cb24584840bcbb1e4be7c8842b5860ffef020354479881ecd", Bytes.byteToHex(bytes));
    }

    @Test
    public void hmacSha512() {
        byte[] bytes = Securities.hmacSha512("Securities.hmacSha512".getBytes(), "Securities.hmacSha512".getBytes());
        assertEquals("df8f877e715892410772338c9bc24071d7f47eb21be2b9ddedb862eb51d11168f1b2164db2e9a7290c57ed0c6aa27cfb0e9a0f6f160b528c5aa64608e8f356c8", Bytes.byteToHex(bytes));
    }

    @Test
    public void encryptAES() {
        byte[] bytes = "Securities.encryptAES".getBytes();
        byte[] key = "Securities.encryptKey".getBytes();
        assertArrayEquals(bytes, Securities.decryptAES(Securities.encryptAES(bytes, key), key));
    }

    @Test
    public void encryptRSA() throws NoSuchAlgorithmException {
        final int keySize = 2048;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        byte[] bytes = "Securities.encryptRSA".getBytes();

        assertArrayEquals(bytes, Securities.decryptRSA(Securities.encryptRSA(bytes, keyPair.getPublic().getEncoded()), keyPair.getPrivate().getEncoded()));

    }
}