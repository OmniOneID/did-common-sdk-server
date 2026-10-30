package org.omnione.did.common.util.digest;

import org.omnione.did.common.exception.ErrorCode;
import org.omnione.did.common.exception.CommonSdkException;
import org.omnione.did.crypto.enums.DigestType;
import org.omnione.did.crypto.exception.CryptoException;
import org.omnione.did.crypto.util.DigestUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class for digest operations.
 * Implements Layer 1 BaseDigestUtil as per OpenDID Common SDK v1.7.
 */
public class BaseDigestUtil {

    /**
     * Generate SHA-256 hash.
     *
     * @param message Message to hash
     * @return SHA-256 hash (byte array)
     * @throws CommonSdkException if hashing fails
     */
    public static byte[] generateHash(String message) throws CommonSdkException {
        if (message == null) {
            throw new CommonSdkException(ErrorCode.JSON_PROCESSING_ERROR, "Message cannot be null");
        }
        return generateHash(message.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Generate SHA-256 hash.
     *
     * @param data Data to hash
     * @return SHA-256 hash (byte array)
     * @throws CommonSdkException if hashing fails
     */
    public static byte[] generateHash(byte[] data) throws CommonSdkException {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(data);
        } catch (NoSuchAlgorithmException e) {
            throw new CommonSdkException(ErrorCode.JSON_PROCESSING_ERROR, "SHA-256 algorithm not found");
        }
    }

    /**
     * Generate SHA-512 hash.
     *
     * @param message Message to hash
     * @return SHA-512 hash (byte array)
     * @throws CommonSdkException if hashing fails
     */
    public static byte[] generateHash512(String message) throws CommonSdkException {
        if (message == null) {
            throw new CommonSdkException(ErrorCode.JSON_PROCESSING_ERROR, "Message cannot be null");
        }
        return generateHash512(message.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Generate SHA-512 hash.
     *
     * @param data Data to hash
     * @return SHA-512 hash (byte array)
     * @throws CommonSdkException if hashing fails
     */
    public static byte[] generateHash512(byte[] data) throws CommonSdkException {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            return digest.digest(data);
        } catch (NoSuchAlgorithmException e) {
            throw new CommonSdkException(ErrorCode.JSON_PROCESSING_ERROR, "SHA-512 algorithm not found");
        }
    }

    /**
     * Generate hash with a specified DigestType (e.g. SHA256, SHA512).
     * Uses OmniOne crypto SDK's DigestUtils — identical to the CA server's BaseDigestUtil.
     *
     * @param data       Data to hash
     * @param digestType Digest algorithm type
     * @return Hash (byte array)
     * @throws CommonSdkException if hashing fails
     */
    public static byte[] generateHash(byte[] data, DigestType digestType) throws CommonSdkException {
        try {
            return DigestUtils.getDigest(data, digestType);
        } catch (CryptoException e) {
            throw new CommonSdkException(ErrorCode.JSON_PROCESSING_ERROR, "Failed to generate hash: " + e.getMessage());
        }
    }

    /**
     * Generate HMAC.
     * Uses HmacSHA256.
     *
     * @param key  Key for HMAC
     * @param data Data to sign
     * @return HMAC value
     * @throws CommonSdkException if HMAC generation fails
     */
    public static byte[] generateHmac(byte[] key, byte[] data) throws CommonSdkException {
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(key, "HmacSHA256");
            sha256_HMAC.init(secret_key);
            return sha256_HMAC.doFinal(data);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new CommonSdkException(ErrorCode.SIGNATURE_VERIFICATION_FAILED,
                    "Failed to generate HMAC: " + e.getMessage());
        }
    }
}
