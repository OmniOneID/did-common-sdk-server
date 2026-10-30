package org.omnione.did.common.util.wallet;

import org.omnione.did.common.datamodel.enums.crypto.EccCurveType;
import org.omnione.did.common.exception.ErrorCode;
import org.omnione.did.common.exception.CommonSdkException;
import org.omnione.did.common.util.digest.BaseDigestUtil;
import org.omnione.did.wallet.enums.WalletEncryptType;
import org.omnione.did.wallet.exception.WalletException;
import org.omnione.did.wallet.key.WalletManagerFactory;
import org.omnione.did.wallet.key.WalletManagerFactory.WalletManagerType;
import org.omnione.did.wallet.key.WalletManagerInterface;
import org.omnione.did.wallet.key.data.CryptoKeyPairInfo;

import java.nio.charset.StandardCharsets;

/**
 * Wallet Utility Class
 */
public class BaseWalletUtil {

    /**
     * Create a new wallet.
     *
     * @param walletFilePath Path to the wallet file
     * @param password       Password for the wallet
     * @return WalletManagerInterface
     * @throws CommonSdkException if wallet creation fails
     */
    public static WalletManagerInterface createWallet(String walletFilePath, String password) throws CommonSdkException {
        try {
            WalletManagerInterface walletManager = WalletManagerFactory.getWalletManager(WalletManagerType.FILE);
            walletManager.create(walletFilePath, password.toCharArray(), WalletEncryptType.AES_256_CBC_PKCS5Padding);
            return walletManager;
        } catch (WalletException e) {
            throw new CommonSdkException(ErrorCode.WALLET_CREATION_FAILED, "Failed to create wallet: " + e.getMessage());
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.WALLET_CREATION_FAILED,
                    "Unexpected error during wallet creation: " + e.getMessage());
        }
    }

    /**
     * Load an existing wallet.
     *
     * @param walletFilePath Path to the wallet file
     * @param password       Password for the wallet
     * @return WalletManagerInterface
     * @throws CommonSdkException if wallet loading fails
     */
    public static WalletManagerInterface loadWallet(String walletFilePath, String password) throws CommonSdkException {
        try {
            WalletManagerInterface walletManager = WalletManagerFactory.getWalletManager(WalletManagerType.FILE);
            walletManager.connect(walletFilePath, password.toCharArray());
            return walletManager;
        } catch (WalletException e) {
            throw new CommonSdkException(ErrorCode.WALLET_LOAD_FAILED, "Failed to load wallet: " + e.getMessage());
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.WALLET_LOAD_FAILED,
                    "Unexpected error during wallet loading: " + e.getMessage());
        }
    }

    /**
     * Generate a key in the wallet.
     *
     * @param walletManager Wallet manager instance
     * @param keyId         Key ID to generate
     * @param curveType     Elliptic curve type
     * @return Key ID
     * @throws CommonSdkException if key generation fails
     */
    public static String generateKey(WalletManagerInterface walletManager, String keyId, EccCurveType curveType)
            throws CommonSdkException {
        try {
            CryptoKeyPairInfo.KeyAlgorithmType algorithmType;
            if (curveType == null) {
                algorithmType = CryptoKeyPairInfo.KeyAlgorithmType.SECP256k1;
            } else if (curveType == EccCurveType.SECP256K1) {
                algorithmType = CryptoKeyPairInfo.KeyAlgorithmType.SECP256k1;
            } else if (curveType == EccCurveType.SECP256R1) {
                algorithmType = CryptoKeyPairInfo.KeyAlgorithmType.SECP256r1;
            } else {
                algorithmType = CryptoKeyPairInfo.KeyAlgorithmType.SECP256k1;
            }

            walletManager.generateRandomKey(keyId, algorithmType);
            return keyId;
        } catch (WalletException e) {
            throw new CommonSdkException(ErrorCode.KEY_GENERATION_FAILED, "Failed to generate key: " + e.getMessage());
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.KEY_GENERATION_FAILED,
                    "Unexpected error during key generation: " + e.getMessage());
        }
    }

    /**
     * Sign data using the wallet.
     *
     * @param walletManager Wallet manager instance
     * @param keyId         Key ID to use for signing
     * @param data          Data to sign
     * @return Signature bytes
     * @throws CommonSdkException if signing fails
     */
    public static byte[] sign(WalletManagerInterface walletManager, String keyId, byte[] data) throws CommonSdkException {
        try {
            return generateCompactSignature(walletManager, keyId, data);
        } catch (CommonSdkException e) {
            throw e;
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.SIGNATURE_GENERATION_FAILED,
                    "Unexpected error during signing: " + e.getMessage());
        }
    }

    /**
     * Generate compact signature.
     *
     * @param walletManager Wallet manager instance
     * @param keyId         Key ID to use for signing
     * @param data          Data to sign
     * @return Compact signature bytes
     * @throws CommonSdkException if signing fails
     */
    public static byte[] generateCompactSignature(WalletManagerInterface walletManager, String keyId, byte[] data)
            throws CommonSdkException {
        try {
            return walletManager.generateCompactSignatureFromHash(keyId, BaseDigestUtil.generateHash(data));
        } catch (WalletException e) {
            throw new CommonSdkException(ErrorCode.SIGNATURE_GENERATION_FAILED,
                    "Failed to generate compact signature: " + e.getMessage());
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.SIGNATURE_GENERATION_FAILED,
                    "Unexpected error during compact signature generation: " + e.getMessage());
        }
    }

    /**
     * Generate compact signature from a plain text string (UTF-8 encoded).
     *
     * @param walletManager Wallet manager instance
     * @param keyId         Key ID to use for signing
     * @param plainText     Plain text string to sign
     * @return Compact signature bytes
     * @throws CommonSdkException if signing fails
     */
    public static byte[] generateCompactSignature(WalletManagerInterface walletManager, String keyId, String plainText)
            throws CommonSdkException {
        return generateCompactSignature(walletManager, keyId, plainText.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Get a file-based WalletManagerInterface instance without connecting to a wallet file.
     *
     * @return WalletManagerInterface (FILE type)
     * @throws CommonSdkException if the factory fails to create the manager
     */
    public static WalletManagerInterface getFileWalletManager() throws CommonSdkException {
        try {
            return WalletManagerFactory.getWalletManager(WalletManagerType.FILE);
        } catch (WalletException e) {
            throw new CommonSdkException(ErrorCode.FAILED_TO_GET_FILE_WALLET_MANAGER,
                    "Failed to get file wallet manager: " + e.getMessage());
        }
    }

    /**
     * Create a new file-based wallet (void return, throws if already exists).
     * Uses AES-256-CBC-PKCS5Padding encryption.
     *
     * @param walletFilePath Path to the wallet file
     * @param password       Password for the wallet
     * @throws CommonSdkException with WALLET_ALREADY_EXISTS if the wallet file already exists,
     *                            or WALLET_CREATION_FAILED for other errors
     */
    public static void createFileWallet(String walletFilePath, String password) throws CommonSdkException {
        try {
            WalletManagerInterface walletManager = WalletManagerFactory.getWalletManager(WalletManagerType.FILE);
            walletManager.create(walletFilePath, password.toCharArray(), WalletEncryptType.AES_256_CBC_PKCS5Padding);
        } catch (WalletException e) {
            if ("SSDKWLT02030".equals(e.getErrorCode())) {
                throw new CommonSdkException(ErrorCode.WALLET_ALREADY_EXISTS,
                        "Wallet already exists: " + e.getMessage());
            }
            throw new CommonSdkException(ErrorCode.WALLET_CREATION_FAILED,
                    "Failed to create wallet: " + e.getMessage());
        }
    }

    /**
     * Connect to an existing file-based wallet.
     * Alias for {@link #loadWallet(String, String)} for naming consistency with the server.
     *
     * @param walletFilePath Path to the wallet file
     * @param password       Password for the wallet
     * @return Connected WalletManagerInterface
     * @throws CommonSdkException if connection fails
     */
    public static WalletManagerInterface connectFileWallet(String walletFilePath, String password)
            throws CommonSdkException {
        return loadWallet(walletFilePath, password);
    }

    /**
     * Generate a SECP256r1 key pair in the wallet with the specified key ID.
     *
     * @param walletManager WalletManagerInterface instance
     * @param keyId         Key ID for the new key pair
     * @throws CommonSdkException with KEY_ALREADY_EXISTS if the key already exists,
     *                            or KEY_GENERATION_FAILED for other errors
     */
    public static void generateKeyPair(WalletManagerInterface walletManager, String keyId) throws CommonSdkException {
        try {
            walletManager.generateRandomKey(keyId, CryptoKeyPairInfo.KeyAlgorithmType.SECP256r1);
        } catch (WalletException e) {
            if ("SSDKWLT02005".equals(e.getErrorCode())) {
                throw new CommonSdkException(ErrorCode.KEY_ALREADY_EXISTS,
                        "Key already exists: " + e.getMessage());
            }
            throw new CommonSdkException(ErrorCode.KEY_GENERATION_FAILED,
                    "Failed to generate key pair: " + e.getMessage());
        }
    }

    /**
     * Create a file wallet, ignoring the error if the wallet already exists.
     *
     * @param walletFilePath Path to the wallet file
     * @param password       Password for the wallet
     * @throws CommonSdkException if creation fails for a reason other than already existing
     */
    public static void createFileWalletSafe(String walletFilePath, String password) throws CommonSdkException {
        try {
            createFileWallet(walletFilePath, password);
        } catch (CommonSdkException e) {
            if (e.getErrorCode() != ErrorCode.WALLET_ALREADY_EXISTS) {
                throw e;
            }
        }
    }

    /**
     * Generate a SECP256r1 key pair, ignoring the error if the key already exists.
     *
     * @param walletManager WalletManagerInterface instance
     * @param keyId         Key ID to generate
     * @throws CommonSdkException if generation fails for a reason other than already existing
     */
    public static void generateKeyPairSafe(WalletManagerInterface walletManager, String keyId)
            throws CommonSdkException {
        try {
            generateKeyPair(walletManager, keyId);
        } catch (CommonSdkException e) {
            if (e.getErrorCode() != ErrorCode.KEY_ALREADY_EXISTS) {
                throw e;
            }
        }
    }

    /**
     * Create wallet if needed, connect it, and generate the specified key IDs safely.
     * Ignores wallet-already-exists and key-already-exists errors.
     *
     * @param walletFilePath Path to the wallet file
     * @param password       Password for the wallet
     * @param keyIds         Key IDs to generate
     * @return Connected WalletManagerInterface
     * @throws CommonSdkException if any step fails unexpectedly
     */
    public static WalletManagerInterface initializeWalletWithKeys(String walletFilePath, String password,
            String... keyIds) throws CommonSdkException {
        createFileWalletSafe(walletFilePath, password);
        WalletManagerInterface walletManager = connectFileWallet(walletFilePath, password);
        for (String keyId : keyIds) {
            generateKeyPairSafe(walletManager, keyId);
        }
        return walletManager;
    }
}
