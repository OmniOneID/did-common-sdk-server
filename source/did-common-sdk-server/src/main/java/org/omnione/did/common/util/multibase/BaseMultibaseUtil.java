package org.omnione.did.common.util.multibase;

import org.omnione.did.common.exception.ErrorCode;
import org.omnione.did.common.exception.CommonSdkException;
import org.omnione.did.crypto.util.MultiBaseUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Utility class for Multibase and Base64 operations.
 * Implements Layer 1 BaseMultibaseUtil as per OpenDID Common SDK v1.7.
 */
public class BaseMultibaseUtil {

    /**
     * Encode data to Multibase string (Base58BTC).
     *
     * @param data Data to encode
     * @return Multibase encoded string
     * @throws CommonSdkException if encoding fails
     */
    public static String encode(byte[] data) throws CommonSdkException {
        try {
            return MultiBaseUtils.encode(data, org.omnione.did.crypto.enums.MultiBaseType.base64);
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.ENCODING_FAILED, "Failed to encode Multibase: " + e.getMessage());
        }
    }

    /**
     * Encode data to Multibase string with specific type.
     *
     * @param data Data to encode
     * @param type Multibase type
     * @return Multibase encoded string
     * @throws CommonSdkException if encoding fails
     */
    public static String encode(byte[] data, MultiBaseType type) throws CommonSdkException {
        if (type == null) {
            return encode(data);
        }
        try {
            return MultiBaseUtils.encode(data, org.omnione.did.crypto.enums.MultiBaseType.valueOf(type.name()));
        } catch (IllegalArgumentException e) {
            throw new CommonSdkException(ErrorCode.ENCODING_FAILED, "Unsupported Multibase type: " + type);
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.ENCODING_FAILED, "Failed to encode Multibase: " + e.getMessage());
        }
    }

    /**
     * Encode data to Multibase string with OmniOne native MultiBaseType.
     *
     * @param data Data to encode
     * @param type OmniOne native Multibase type
     * @return Multibase encoded string
     * @throws CommonSdkException if encoding fails
     */
    public static String encode(byte[] data, org.omnione.did.crypto.enums.MultiBaseType type) throws CommonSdkException {
        try {
            return MultiBaseUtils.encode(data, type);
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.ENCODING_FAILED, "Failed to encode Multibase: " + e.getMessage());
        }
    }

    /**
     * Decode Multibase string.
     *
     * @param encoded Multibase encoded string
     * @return Decoded bytes
     * @throws CommonSdkException if decoding fails
     */
    public static byte[] decode(String encoded) throws CommonSdkException {
        try {
            byte[] decoded = MultiBaseUtils.decode(encoded);
            if (decoded == null) {
                throw new CommonSdkException(
                        ErrorCode.ENCODING_FAILED,
                        "Failed to decode Multibase: unsupported or invalid encoding");
            }
            return decoded;
        } catch (CommonSdkException e) {
            throw e;
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.ENCODING_FAILED, "Failed to decode Multibase: " + e.getMessage());
        }
    }

    /**
     * Encode data to Base64 string.
     *
     * @param data Data to encode
     * @return Base64 encoded string
     * @throws CommonSdkException if encoding fails
     */
    public static String encodeBase64(byte[] data) throws CommonSdkException {
        try {
            return Base64.getEncoder().encodeToString(data);
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.ENCODING_FAILED, "Failed to encode Base64: " + e.getMessage());
        }
    }

    /**
     * Decode Base64 string.
     *
     * @param encoded Base64 encoded string
     * @return Decoded bytes
     * @throws CommonSdkException if decoding fails
     */
    public static byte[] decodeBase64(String encoded) throws CommonSdkException {
        try {
            return Base64.getDecoder().decode(encoded);
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.ENCODING_FAILED, "Failed to decode Base64: " + e.getMessage());
        }
    }
}
