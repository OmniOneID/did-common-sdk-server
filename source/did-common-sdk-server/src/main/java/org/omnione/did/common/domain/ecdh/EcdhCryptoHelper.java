package org.omnione.did.common.domain.ecdh;

import org.omnione.did.common.datamodel.enums.crypto.EccCurveType;
import org.omnione.did.common.datamodel.enums.crypto.SymmetricCipherType;
import org.omnione.did.common.exception.CommonSdkException;
import org.omnione.did.common.util.crypto.BaseCryptoUtil;
import org.omnione.did.common.datamodel.data.ecdh.Candidate;
import org.omnione.did.common.exception.ErrorCode;
import java.util.List;

/**
 * EcdhCryptoLogic implementing Layer 2 ECDH domain logic.
 */
public class EcdhCryptoHelper {

    /**
     * Generates a session key using ECDH shared secret and a nonce.
     *
     * @param clientPublicKey  The client's public key.
     * @param serverPrivateKey The server's private key.
     * @param mergedNonce      The merged nonce (client nonce + server nonce).
     * @param cipherType       The symmetric cipher type.
     * @param curveType        The elliptic curve type.
     * @return The generated session key.
     * @throws CommonSdkException If key generation fails.
     */
    public static byte[] generateSessionKey(
            byte[] clientPublicKey,
            byte[] serverPrivateKey,
            byte[] mergedNonce,
            SymmetricCipherType cipherType,
            EccCurveType curveType) throws CommonSdkException {
        return generateSessionKey(clientPublicKey, serverPrivateKey, mergedNonce, cipherType, curveType, false);
    }

    /**
     * Generates a session key using ECDH shared secret and a nonce, with optional
     * hashing.
     *
     * @param clientPublicKey   The client's public key.
     * @param serverPrivateKey  The server's private key.
     * @param mergedNonce       The merged nonce (client nonce + server nonce).
     * @param cipherType        The symmetric cipher type.
     * @param curveType         The elliptic curve type.
     * @param applyNonceHashing Whether to apply SHA-256 hashing to the merged nonce
     *                          before key derivation.
     * @return The generated session key.
     * @throws CommonSdkException If key generation fails.
     */
    public static byte[] generateSessionKey(
            byte[] clientPublicKey,
            byte[] serverPrivateKey,
            byte[] mergedNonce,
            SymmetricCipherType cipherType,
            EccCurveType curveType,
            boolean applyNonceHashing) throws CommonSdkException {
        if (clientPublicKey == null || serverPrivateKey == null || mergedNonce == null
                || cipherType == null || curveType == null) {
            throw new CommonSdkException(ErrorCode.INVALID_PARAMETER);
        }

        // 1. Generate Shared Secret
        byte[] sharedSecret = BaseCryptoUtil.generateSharedSecret(clientPublicKey, serverPrivateKey, curveType);

        // 2. Hash Nonce if requested (Legacy Support)
        byte[] finalNonce = mergedNonce;
        if (applyNonceHashing) {
            finalNonce = org.omnione.did.common.util.digest.BaseDigestUtil.generateHash(mergedNonce);
        }

        // 3. Merge Shared Secret and Nonce to derive Session Key
        return BaseCryptoUtil.mergeSharedSecretAndNonce(sharedSecret, finalNonce, cipherType);
    }

    /**
     * Verifies the generated session key against an expected session key.
     *
     * @param sessionKey         The generated session key.
     * @param expectedSessionKey The expected session key.
     * @return True if the keys match, false otherwise.
     * @throws CommonSdkException If verification fails.
     */
    public static boolean verifySessionKey(
            byte[] sessionKey,
            byte[] expectedSessionKey) throws CommonSdkException {
        if (sessionKey == null || expectedSessionKey == null) {
            throw new CommonSdkException(ErrorCode.INVALID_PARAMETER);
        }

        return java.security.MessageDigest.isEqual(sessionKey, expectedSessionKey);
    }

    /**
     * Selects the cipher type from the candidate list that matches the server's
     * cipher type.
     *
     * @param candidate        The candidate object containing supported cipher
     *                         types.
     * @param serverCipherType The server's preferred cipher type.
     * @return The selected symmetric cipher type.
     * @throws CommonSdkException If no matching cipher type is found.
     */
    public static SymmetricCipherType selectCipherType(
            Candidate candidate,
            SymmetricCipherType serverCipherType) throws CommonSdkException {
        if (serverCipherType == null) {
            throw new CommonSdkException(ErrorCode.INVALID_PARAMETER);
        }

        if (candidate == null) {
            return serverCipherType;
        }

        List<SymmetricCipherType> ciphers = candidate.getCiphers();
        if (ciphers == null || ciphers.isEmpty() || !ciphers.contains(serverCipherType)) {
            throw new CommonSdkException(ErrorCode.CRYPTO_UNSUPPORTED_ALGORITHM);
        }

        return serverCipherType;
    }
}
