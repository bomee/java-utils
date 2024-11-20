package net.bomee.util;

import org.junit.Test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertArrayEquals;

public class CryptosTest {

    @Test
    public void encryptAES() {
        byte[] bytes = "Securities.encryptAES".getBytes();
        byte[] key = "Securities.encryptKey".getBytes();
        assertArrayEquals(bytes, Cryptos.decryptAES(Cryptos.encryptAES(bytes, key), key));
    }

    @Test
    public void encryptRSA() throws NoSuchAlgorithmException {
        final int keySize = 2048;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        byte[] bytes = "Securities.encryptRSA".getBytes();

        assertArrayEquals(bytes, Cryptos.decryptRSA(Cryptos.encryptRSA(bytes, keyPair.getPublic().getEncoded()), keyPair.getPrivate().getEncoded()));

    }

}
