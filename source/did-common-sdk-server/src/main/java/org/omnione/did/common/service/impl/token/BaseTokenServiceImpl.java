package org.omnione.did.common.service.impl.token;

import org.omnione.did.common.datamodel.data.token.TokenData;
import org.omnione.did.common.exception.ErrorCode;
import org.omnione.did.common.exception.CommonSdkException;
import org.omnione.did.common.service.token.TokenService;
import org.omnione.did.common.util.token.BaseAccessTokenUtil;
import org.omnione.did.wallet.key.WalletManagerInterface;

public abstract class BaseTokenServiceImpl implements TokenService {

    @Override
    public String generateAccessToken(
            TokenData tokenData,
            long expirationSeconds) throws CommonSdkException {
        // 1. Token 데이터 검증
        validateTokenData(tokenData);

        // 2. Token 생성
        String token = BaseAccessTokenUtil.generateAccessToken(
                getWalletManager(),
                getSigningKeyId(),
                tokenData,
                expirationSeconds);

        // 3. Token 저장
        saveToken(token, tokenData);

        return token;
    }

    @Override
    public TokenData verifyAccessToken(String token) throws CommonSdkException {
        // 1. Token 검증
        TokenData tokenData = BaseAccessTokenUtil.verifyAccessToken(
                token,
                getPublicKey());

        // 2. 만료 검증
        validateExpiration(tokenData.getExpirationTime());

        // 3. 폐기 여부 확인
        checkTokenRevocation(token);

        return tokenData;
    }

    @Override
    public String refreshToken(String refreshToken) throws CommonSdkException {
        // SoT 문서에 구현 흐름이 없으므로 예외 처리
        throw new CommonSdkException(ErrorCode.NOT_SUPPORTED_OPERATION);
    }

    protected void validateTokenData(TokenData tokenData) throws CommonSdkException {
        if (tokenData == null) {
            throw new CommonSdkException(ErrorCode.INVALID_PARAMETER);
        }
    }

    protected void validateExpiration(long expirationTime) throws CommonSdkException {
        long currentTime = System.currentTimeMillis() / 1000;
        if (currentTime > expirationTime) {
            throw new CommonSdkException(ErrorCode.EXPIRED_TOKEN);
        }
    }

    // 서버별 구현 필요
    protected abstract WalletManagerInterface getWalletManager() throws CommonSdkException;

    protected abstract String getSigningKeyId() throws CommonSdkException;

    protected abstract String getPublicKey() throws CommonSdkException;

    protected abstract void saveToken(String token, TokenData tokenData) throws CommonSdkException;

    protected abstract void checkTokenRevocation(String token) throws CommonSdkException;
}
