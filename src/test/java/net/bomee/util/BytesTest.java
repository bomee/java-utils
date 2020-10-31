package net.bomee.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class BytesTest {

    @Test
    public void byte2Hex() {
        assertEquals("00", Bytes.byteToHex(new byte[]{(byte) 0x00}));
        assertEquals("01", Bytes.byteToHex(new byte[]{(byte) 0x01}));
        assertEquals("ff", Bytes.byteToHex(new byte[]{(byte) 0xff}));
        assertEquals("00eeff", Bytes.byteToHex(new byte[]{(byte) 0x00, (byte) 0xee, (byte) 0xff}));
    }

    @Test
    public void hex2Byte() {
        assertArrayEquals(new byte[]{(byte) 0x00, (byte) 0xee, (byte) 0xff}, Bytes.hexToByte("00eeff"));
    }
}