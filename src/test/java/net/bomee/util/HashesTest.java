package net.bomee.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HashesTest {

    @Test
    public void crc32() {
        assertEquals(3604621417L, Hashes.crc32("Securities.crc32".getBytes()));
    }

    @Test
    public void md5() {
        byte[] bytes = Hashes.md5("Securities.md5".getBytes());
        assertEquals("7e6a7b304ba09685378984c2f2271e3d", Bytes.byteToHex(bytes));
    }

    @Test
    public void sha1() {
        byte[] bytes = Hashes.sha1("Securities.sha1".getBytes());
        assertEquals("f7233a6576fac8a283ea56f38fdf01054c7fd958", Bytes.byteToHex(bytes));
    }

    @Test
    public void sha256() {
        byte[] bytes = Hashes.sha256("Securities.sha256".getBytes());
        assertEquals("5c259158221dc11dc9b5c65e6f5a6b36979a4e11d851a51ad1fe4e59c98de261", Bytes.byteToHex(bytes));
    }

    @Test
    public void sha512() {
        byte[] bytes = Hashes.sha512("Securities.sha512".getBytes());
        assertEquals("158a1f52a55b766ceae754cb666772737fd4327a9257a264490b8b2d2d41c365962717154d2fd40b63f75e018bc9469381890d48327643c72d29033f853fce9e", Bytes.byteToHex(bytes));
    }

    @Test
    public void hmacMD5() {
        byte[] bytes = Hashes.hmacMD5("Securities.hmacMD5".getBytes(), "Securities.hmacMD5".getBytes());
        assertEquals("bab9b32b01f8e0665a4a5aeb20b22ddb", Bytes.byteToHex(bytes));
    }

    @Test
    public void hmacSha1() {
        assertEquals(
                "1801a34930f75339f053da290e3a41e88fcf82b0",
                Hashes.hmacSha1("Securities.hmacSha1", "Securities.hmacSha1")
        );
    }

    @Test
    public void hmacSha256() {
        assertEquals(
                "875f5d4c888f091cb24584840bcbb1e4be7c8842b5860ffef020354479881ecd",
                Hashes.hmacSha256("Securities.hmacSha256", "Securities.hmacSha256")
        );
    }

    @Test
    public void hmacSha512() {
        byte[] bytes = Hashes.hmacSha512("Securities.hmacSha512".getBytes(), "Securities.hmacSha512".getBytes());
        assertEquals(
                "df8f877e715892410772338c9bc24071d7f47eb21be2b9ddedb862eb51d11168f1b2164db2e9a7290c57ed0c6aa27cfb0e9a0f6f160b528c5aa64608e8f356c8",
                Hashes.hmacSha512("Securities.hmacSha512", "Securities.hmacSha512")
        );
    }
}
