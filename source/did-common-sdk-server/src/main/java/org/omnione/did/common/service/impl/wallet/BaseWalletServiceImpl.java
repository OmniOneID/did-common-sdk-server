package org.omnione.did.common.service.impl.wallet;

import org.omnione.did.common.datamodel.enums.crypto.EccCurveType;
import org.omnione.did.common.exception.CommonSdkException;
import org.omnione.did.common.service.wallet.WalletService;
import org.omnione.did.common.util.wallet.BaseWalletUtil;
import org.omnione.did.wallet.key.WalletManagerInterface;

/**
 * Base Wallet Service Implementation
 */
public abstract class BaseWalletServiceImpl implements WalletService {

    protected WalletManagerInterface walletManager;

    @Override
    public WalletManagerInterface createWallet(
            String walletFilePath,
            String password) throws CommonSdkException {
        // 1. Wallet 생성
        this.walletManager = BaseWalletUtil.createWallet(walletFilePath, password);

        // 2. 기본 키 생성
        initializeDefaultKeys();

        return this.walletManager;
    }

    @Override
    public WalletManagerInterface loadWallet(
            String walletFilePath,
            String password) throws CommonSdkException {
        this.walletManager = BaseWalletUtil.loadWallet(walletFilePath, password);
        return this.walletManager;
    }

    @Override
    public String generateKey(
            String keyId,
            EccCurveType curveType) throws CommonSdkException {
        return BaseWalletUtil.generateKey(this.walletManager, keyId, curveType);
    }

    @Override
    public byte[] sign(
            String keyId,
            byte[] data) throws CommonSdkException {
        return BaseWalletUtil.generateCompactSignature(this.walletManager, keyId, data);
    }

    // 서버별 구현 필요
    protected abstract void initializeDefaultKeys() throws CommonSdkException;
}
