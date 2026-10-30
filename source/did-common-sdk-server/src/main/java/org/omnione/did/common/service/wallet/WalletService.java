package org.omnione.did.common.service.wallet;

import org.omnione.did.common.datamodel.enums.crypto.EccCurveType;
import org.omnione.did.common.exception.CommonSdkException;
import org.omnione.did.wallet.key.WalletManagerInterface;

/**
 * Wallet Service Interface
 */
public interface WalletService {
    /**
     * Create a new wallet.
     *
     * @param walletFilePath Path to the wallet file
     * @param password       Password for the wallet
     * @return WalletManagerInterface
     * @throws CommonSdkException if wallet creation fails
     */
    WalletManagerInterface createWallet(String walletFilePath, String password) throws CommonSdkException;

    /**
     * Load an existing wallet.
     *
     * @param walletFilePath Path to the wallet file
     * @param password       Password for the wallet
     * @return WalletManagerInterface
     * @throws CommonSdkException if wallet loading fails
     */
    WalletManagerInterface loadWallet(String walletFilePath, String password) throws CommonSdkException;

    /**
     * Generate a key in the wallet.
     *
     * @param keyId     Key ID to generate
     * @param curveType Elliptic curve type
     * @return Key ID
     * @throws CommonSdkException if key generation fails
     */
    String generateKey(String keyId, EccCurveType curveType) throws CommonSdkException;

    /**
     * Sign data using the wallet.
     *
     * @param keyId Key ID to use for signing
     * @param data  Data to sign
     * @return Signature bytes
     * @throws CommonSdkException if signing fails
     */
    byte[] sign(String keyId, byte[] data) throws CommonSdkException;
}
