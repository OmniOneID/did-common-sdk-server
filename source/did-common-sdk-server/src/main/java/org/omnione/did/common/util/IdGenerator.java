package org.omnione.did.common.util;

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
}
