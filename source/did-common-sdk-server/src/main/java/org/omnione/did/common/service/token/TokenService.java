package org.omnione.did.common.service.token;

import org.omnione.did.common.datamodel.data.token.TokenData;
import org.omnione.did.common.exception.CommonSdkException;

/**
 * Token Service Interface.
 */
public interface TokenService {
    /**
     * Access Token 생성
     */
    String generateAccessToken(TokenData tokenData, long expirationSeconds) throws CommonSdkException;

    /**
     * Access Token 검증
     */
    TokenData verifyAccessToken(String token) throws CommonSdkException;

    /**
     * Token 갱신
     */
    String refreshToken(String refreshToken) throws CommonSdkException;
}
