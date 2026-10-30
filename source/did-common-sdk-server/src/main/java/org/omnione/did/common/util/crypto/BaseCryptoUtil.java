package org.omnione.did.common.util.crypto;

import org.omnione.did.common.datamodel.enums.crypto.EccCurveType;
import org.omnione.did.common.datamodel.enums.crypto.SymmetricCipherType;
import org.omnione.did.common.datamodel.enums.crypto.SymmetricPaddingType;
import org.omnione.did.common.exception.ErrorCode;
import org.omnione.did.common.exception.CommonSdkException;
import org.omnione.did.crypto.engines.CipherInfo;
import org.omnione.did.crypto.exception.CryptoException;
import org.omnione.did.crypto.keypair.KeyPairInterface;
import org.omnione.did.crypto.util.CryptoUtils;
import org.omnione.did.crypto.util.DigestUtils;
import org.omnione.did.crypto.util.MultiBaseUtils;
import org.omnione.did.crypto.util.SignatureUtils;
import org.omnione.did.crypto.enums.DidKeyType;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Utility class for cryptographic operations.
 * Implements Layer 1 BaseCryptoUtil as per OpenDID Common SDK v1.7.
 */
public class BaseCryptoUtil {

    /**
     * Generate a key pair.
     * The key pair is generated using the specified ECC curve type.
     *
     * @param curveType The ECC curve type to use for key pair generation
     * @return The generated key pair
     * @throws CommonSdkException if key pair generation fails
     */
    public static KeyPairInterface generateKeyPair(EccCurveType curveType) throws CommonSdkException {
        try {
            return CryptoUtils.generateKeyPair(toOmnioneDidKeyType(curveType));
        } catch (CryptoException e) {
            throw new CommonSdkException(ErrorCode.ECDH_KEY_GENERATION_FAILED);
        }
    }

    /**
     * Generate a shared secret.
     * The shared secret is generated using the public key and private key.
     *
     * @param publicKey  The public key
     * @param privateKey The private key
     * @param curveType  The ECC curve type
     * @return The generated shared secret
     * @throws CommonSdkException if shared secret generation fails
     */
    public static byte[] generateSharedSecret(byte[] publicKey, byte[] privateKey, EccCurveType curveType)
            throws CommonSdkException {
        try {
            return CryptoUtils.generateSharedSecret(publicKey, privateKey, toOmnioneEccCurveType(curveType));
        } catch (CryptoException e) {
            throw new CommonSdkException(ErrorCode.SESSION_KEY_GENERATION_FAILED);
        }
    }

    /**
     * Generate a nonce.
     * The nonce is generated with the specified length.
     *
     * @param length The length of the nonce to generate
     * @return The generated nonce
     * @throws CommonSdkException if nonce generation fails
     */
    public static byte[] generateNonce(int length) throws CommonSdkException {
        try {
            return CryptoUtils.generateNonce(length);
        } catch (CryptoException e) {
            throw new CommonSdkException(ErrorCode.JSON_PROCESSING_ERROR, "Failed to generate nonce: " + e.getMessage());
        }
    }

    /**
     * Verifies a digital signature using the provided public key and ECC curve
     * type.
     *
     * @param encodedPublicKey The encoded public key.
     * @param signature        The encoded signature.
     * @param message          The data to verify.
     * @param curveType        The ECC curve type.
     * @throws CommonSdkException if signature verification fails.
     */
    public static void verifySignature(String encodedPublicKey, String signature, byte[] message,
            EccCurveType curveType) throws CommonSdkException {
        try {
            // Decode the public key
            byte[] publicKeyBytes = MultiBaseUtils.decode(encodedPublicKey);
            byte[] signatureBytes = MultiBaseUtils.decode(signature);

            // Verify the signature
            SignatureUtils.verifyCompactSignWithCompressedKey(publicKeyBytes, message, signatureBytes,
                    toOmnioneEccCurveType(curveType));
        } catch (CryptoException e) {
            throw new CommonSdkException(ErrorCode.SIGNATURE_VERIFICATION_FAILED);
        }
    }

    /**
     * Verifies a digital signature using raw signature bytes (e.g. decoded from base64url JWT signatures).
     *
     * @param encodedPublicKey The multibase-encoded public key.
     * @param signatureBytes   The raw signature bytes.
     * @param message          The data to verify.
     * @param curveType        The ECC curve type.
     * @throws CommonSdkException if signature verification fails.
     */
    public static void verifySignature(String encodedPublicKey, byte[] signatureBytes, byte[] message,
            EccCurveType curveType) throws CommonSdkException {
        try {
            byte[] publicKeyBytes = MultiBaseUtils.decode(encodedPublicKey);
            SignatureUtils.verifyCompactSignWithCompressedKey(publicKeyBytes, message, signatureBytes,
                    toOmnioneEccCurveType(curveType));
        } catch (CryptoException e) {
            throw new CommonSdkException(ErrorCode.SIGNATURE_VERIFICATION_FAILED);
        }
    }

    /**
     * Compresses a public key using the specified ECC curve type.
     *
     * @param publicKey The uncompressed public key.
     * @param curveType The ECC curve type.
     * @return The compressed public key.
     * @throws CommonSdkException if public key compression fails.
     */
    public static byte[] compressPublicKey(byte[] publicKey, EccCurveType curveType) throws CommonSdkException {
        try {
            return CryptoUtils.compressPublicKey(publicKey, toOmnioneEccCurveType(curveType));
        } catch (CryptoException e) {
            throw new CommonSdkException(ErrorCode.ECDH_KEY_GENERATION_FAILED);
        }
    }

    /**
     * Decompresses a public key using the specified ECC curve type.
     *
     * @param compressedKey The compressed public key.
     * @param curveType     The ECC curve type.
     * @return The uncompressed public key.
     * @throws CommonSdkException if public key decompression fails.
     */
    public static byte[] decompressPublicKey(byte[] compressedKey, EccCurveType curveType) throws CommonSdkException {
        try {
            return CryptoUtils.unCompressPublicKey(compressedKey, toOmnioneEccCurveType(curveType));
        } catch (CryptoException e) {
            throw new CommonSdkException(ErrorCode.ECDH_KEY_GENERATION_FAILED);
        }
    }

    /**
     * Merge a shared secret and nonce.
     *
     * @param sharedSecret The shared secret
     * @param nonce        The nonce
     * @param cipherType   The symmetric cipher type
     * @return The merged shared secret and nonce
     * @throws CommonSdkException if shared secret and nonce merge fails
     */
    public static byte[] mergeSharedSecretAndNonce(byte[] sharedSecret, byte[] nonce, SymmetricCipherType cipherType)
            throws CommonSdkException {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(sharedSecret, 0, sharedSecret.length);
            digest.update(nonce, 0, nonce.length);
            byte[] combinedResult = digest.digest();

            int length;
            if (cipherType == SymmetricCipherType.AES_128_CBC || cipherType == SymmetricCipherType.AES_128_ECB) {
                length = 16;
            } else if (cipherType == SymmetricCipherType.AES_256_CBC || cipherType == SymmetricCipherType.AES_256_ECB) {
                length = 32;
            } else {
                throw new CommonSdkException(ErrorCode.SESSION_KEY_GENERATION_FAILED);
            }
            return Arrays.copyOfRange(combinedResult, 0, length);

        } catch (NoSuchAlgorithmException e) {
            throw new CommonSdkException(ErrorCode.SESSION_KEY_GENERATION_FAILED);
        }
    }

    /**
     * Encrypt data.
     *
     * @param plainText   The plain text to encrypt
     * @param key         The key to use for encryption
     * @param iv          The initial vector to use for encryption
     * @param cipherType  The symmetric cipher type
     * @param paddingType The symmetric padding type
     * @return The encrypted data
     * @throws CommonSdkException if encryption fails
     */
    public static byte[] encrypt(byte[] plainText, byte[] key, byte[] iv, SymmetricCipherType cipherType,
            SymmetricPaddingType paddingType) throws CommonSdkException {
        try {
            CipherInfo cipherInfo = new CipherInfo(toOmnioneSymmetricCipherType(cipherType),
                    toOmnioneSymmetricPaddingType(paddingType));
            return CryptoUtils.encrypt(plainText, cipherInfo, key, iv);
        } catch (CryptoException e) {
            throw new CommonSdkException(ErrorCode.ENCODING_FAILED);
        }
    }

    /**
     * Decrypt data.
     *
     * @param cipherText  The encrypted data to decrypt
     * @param key         The key to use for decryption
     * @param iv          The initial vector to use for decryption
     * @param cipherType  The symmetric cipher type
     * @param paddingType The symmetric padding type
     * @return The decrypted data
     * @throws CommonSdkException if decryption fails
     */
    public static byte[] decrypt(byte[] cipherText, byte[] key, byte[] iv, SymmetricCipherType cipherType,
            SymmetricPaddingType paddingType) throws CommonSdkException {
        try {
            CipherInfo cipherInfo = new CipherInfo(toOmnioneSymmetricCipherType(cipherType),
                    toOmnioneSymmetricPaddingType(paddingType));
            return CryptoUtils.decrypt(cipherText, cipherInfo, key, iv);
        } catch (CryptoException e) {
            throw new CommonSdkException(ErrorCode.ENCODING_FAILED);
        }
    }

    /**
     * Merge two nonces by hashing them together (SHA-256).
     *
     * @param clientNonce Client nonce bytes
     * @param serverNonce Server nonce bytes
     * @return Merged nonce bytes
     * @throws CommonSdkException if merge fails
     */
    public static byte[] mergeNonce(byte[] clientNonce, byte[] serverNonce) throws CommonSdkException {
        try {
            return DigestUtils.mergeNonce(clientNonce, serverNonce);
        } catch (CryptoException e) {
            throw new CommonSdkException(ErrorCode.NONCE_MERGE_FAILED);
        }
    }

    /**
     * Merge two multibase-encoded nonces.
     *
     * @param encodedClientNonce Multibase-encoded client nonce
     * @param encodedServerNonce Multibase-encoded server nonce
     * @return Merged nonce bytes
     * @throws CommonSdkException if decoding or merge fails
     */
    public static byte[] mergeNonce(String encodedClientNonce, String encodedServerNonce) throws CommonSdkException {
        try {
            byte[] clientNonce = MultiBaseUtils.decode(encodedClientNonce);
            byte[] serverNonce = MultiBaseUtils.decode(encodedServerNonce);
            return mergeNonce(clientNonce, serverNonce);
        } catch (CryptoException e) {
            throw new CommonSdkException(ErrorCode.NONCE_MERGE_FAILED);
        }
    }

    /**
     * Decrypt multibase-encoded cipher text.
     *
     * @param encodedCipherText Multibase-encoded encrypted data
     * @param key               Decryption key
     * @param iv                Initialization vector
     * @param cipherType        Symmetric cipher type
     * @param paddingType       Symmetric padding type
     * @return Decrypted bytes
     * @throws CommonSdkException if decryption fails
     */
    public static byte[] decrypt(String encodedCipherText, byte[] key, byte[] iv, SymmetricCipherType cipherType,
            SymmetricPaddingType paddingType) throws CommonSdkException {
        try {
            byte[] cipherBytes = MultiBaseUtils.decode(encodedCipherText);
            return decrypt(cipherBytes, key, iv, cipherType, paddingType);
        } catch (CryptoException e) {
            throw new CommonSdkException(ErrorCode.ENCODING_FAILED, "Failed to decode cipher text: " + e.getMessage());
        }
    }

    // Private helper methods for Enum conversion

    private static org.omnione.did.crypto.enums.EccCurveType toOmnioneEccCurveType(EccCurveType type) {
        switch (type) {
            case SECP256K1:
                return org.omnione.did.crypto.enums.EccCurveType.Secp256k1;
            case SECP256R1:
                return org.omnione.did.crypto.enums.EccCurveType.Secp256r1;
            default:
                throw new CommonSdkException(ErrorCode.CRYPTO_UNSUPPORTED_ALGORITHM,
                        "Unsupported ECC curve type: " + type);
        }
    }

    private static DidKeyType toOmnioneDidKeyType(EccCurveType type) {
        switch (type) {
            case SECP256K1:
                return DidKeyType.SECP256K1_VERIFICATION_KEY_2018;
            case SECP256R1:
                return DidKeyType.SECP256R1_VERIFICATION_KEY_2018;
            default:
                throw new CommonSdkException(ErrorCode.CRYPTO_UNSUPPORTED_ALGORITHM,
                        "Unsupported ECC curve type for DID Key: " + type);
        }
    }

    private static org.omnione.did.crypto.enums.SymmetricCipherType toOmnioneSymmetricCipherType(
            SymmetricCipherType type) {
        switch (type) {
            case AES_128_CBC:
                return org.omnione.did.crypto.enums.SymmetricCipherType.AES_128_CBC;
            case AES_128_ECB:
                return org.omnione.did.crypto.enums.SymmetricCipherType.AES_128_ECB;
            case AES_256_CBC:
                return org.omnione.did.crypto.enums.SymmetricCipherType.AES_256_CBC;
            case AES_256_ECB:
                return org.omnione.did.crypto.enums.SymmetricCipherType.AES_256_ECB;
            default:
                throw new CommonSdkException(ErrorCode.CRYPTO_UNSUPPORTED_ALGORITHM,
                        "Unsupported symmetric cipher type: " + type);
        }
    }

    private static org.omnione.did.crypto.enums.SymmetricPaddingType toOmnioneSymmetricPaddingType(
            SymmetricPaddingType type) {
        switch (type) {
            case NOPAD:
                return org.omnione.did.crypto.enums.SymmetricPaddingType.NOPAD;
            case PKCS5:
                return org.omnione.did.crypto.enums.SymmetricPaddingType.PKCS5;
            default:
                throw new CommonSdkException(ErrorCode.CRYPTO_UNSUPPORTED_ALGORITHM,
                        "Unsupported symmetric padding type: " + type);
        }
    }
}
