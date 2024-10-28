package org.omnione.did.common.util;

import java.security.SecureRandom;
import java.util.Arrays;

/**
 * NonceGenerator is a utility class that provides methods for generating
 * cryptographically secure random nonces. These nonces are often used
 * in security protocols to prevent replay attacks and ensure uniqueness.
 */
public class NonceGenerator {
    /**
     * Generate a 16-byte random nonce.
     * @return 16-byte random nonce
     */
    public static byte[] generate16ByteNonce() {
        SecureRandom random = new SecureRandom();
        byte[] nonce = new byte[16];
        random.nextBytes(nonce);

        return nonce;
    }
}
