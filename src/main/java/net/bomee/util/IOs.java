package net.bomee.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 常见的IO操作
 *
 * @author bomee shiaupo@qq.com
 */
public final class IOs {
    private IOs() {
    }

    /**
     * 默认 buffer size，8k
     */
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 8;

    /**
     * read stream to byte
     *
     * @param input      InputStream
     * @param bufferSize buffer size
     * @return byte array
     * @throws IOException IOException
     */
    public static byte[] readToByteArray(InputStream input, int bufferSize) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[bufferSize];
        int count;
        while ((count = input.read(buffer)) != -1) {
            output.write(buffer, 0, count);
        }
        return output.toByteArray();
    }

    /**
     * read stream to byte with default buffer size.
     *
     * @param input InputStream
     * @return byte array
     * @throws IOException IOException
     */
    public static byte[] readToByteArray(InputStream input) throws IOException {
        return readToByteArray(input, DEFAULT_BUFFER_SIZE);
    }

    /**
     * readToUTF8String
     *
     * @param input InputStream
     * @return String
     * @throws IOException IOException
     */
    public static String readToUTF8String(InputStream input) throws IOException {
        return Bytes.byteToUTF8String(readToByteArray(input));
    }

    /**
     * readToString
     *
     * @param input       InputStream
     * @param charsetName charset name.
     * @return String
     * @throws IOException IOException
     */
    public static String readToString(InputStream input, String charsetName) throws IOException {
        return Bytes.byteToString(readToByteArray(input), charsetName);
    }
}
