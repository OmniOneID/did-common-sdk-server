package org.omnione.did.common.util;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Utility class for generating UUIDs.
 * This class provides methods for generating random Transaction IDs and Offer IDs.
 */
public class IdGenerator {

    /**
     * Generate a random Transaction ID.
     * This method uses the built-in Java UUID class to generate a random UUID string.
     * @return a random Transaction ID
     */
    public static String generateTxId() {
        return UUID.randomUUID().toString();
    }

    /**
     * Generate a random Offer ID.
     * This method uses the built-in Java UUID class to generate a random UUID string.
     * @return a random Offer ID
     */
    public static String generateOfferId() {
        return UUID.randomUUID().toString();
    }

    /**
     * Generate a random UUID string.
     *
     * @return UUID as a string (e.g. "550e8400-e29b-41d4-a716-446655440000")
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * Generate a random reference ID (17-character alphanumeric string).
     *
     * @return reference ID string
     */
    public static String generateRefId() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(17);
        for (int i = 0; i < 17; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    /**
     * Generate a message ID composed of a timestamp and a random hex suffix.
     * Format: {@code yyyyMMddHHmmss + microseconds(6-digit) + random-hex(8-char)}
     *
     * @return message ID string
     */
    public static String generateMessageId() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String currentDateAndTime = dateFormat.format(new Date());

        long currentTimeMillis = System.currentTimeMillis();
        long microsecond = (currentTimeMillis % 1000) * 1000;

        String randomHexString = UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 8);

        return currentDateAndTime +
                String.format("%06d", microsecond) +
                randomHexString;
    }
}
