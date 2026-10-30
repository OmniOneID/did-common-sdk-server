package org.omnione.did.common.datamodel.data.token;

/**
 * Shell class for TokenData to support TokenService.
 * Actual fields to be defined based on implementation requirements or updated
 * SoT.
 */
public class TokenData {
    private long expirationTime;

    public TokenData() {
    }

    public long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
    }

}
