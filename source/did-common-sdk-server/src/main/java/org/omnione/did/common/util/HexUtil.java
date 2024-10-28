package org.omnione.did.common.util;

/**
 * Utility class for converting byte arrays to hexadecimal string representation.
 * This class provides a method to convert an array of bytes into a hexadecimal string.
 *
 */
public class HexUtil {
    public static String toHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }
}
