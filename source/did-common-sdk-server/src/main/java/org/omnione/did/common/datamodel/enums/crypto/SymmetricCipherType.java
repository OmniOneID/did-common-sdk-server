package org.omnione.did.common.datamodel.enums.crypto;

import com.google.gson.annotations.SerializedName;
import com.fasterxml.jackson.annotation.JsonValue;
import org.omnione.did.common.exception.CommonSdkException;
import org.omnione.did.common.exception.ErrorCode;
import java.util.Arrays;

public enum SymmetricCipherType {
    @SerializedName("AES-128-CBC")
    AES_128_CBC("AES-128-CBC"),
    @SerializedName("AES-128-ECB")
    AES_128_ECB("AES-128-ECB"),
    @SerializedName("AES-256-CBC")
    AES_256_CBC("AES-256-CBC"),
    @SerializedName("AES-256-ECB")
    AES_256_ECB("AES-256-ECB");

    private final String displayName;

    SymmetricCipherType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    @JsonValue
    public String toString() {
        return displayName;
    }

    public static SymmetricCipherType fromDisplayName(String displayName) {
        return Arrays.stream(SymmetricCipherType.values())
                .filter(type -> type.displayName.equals(displayName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No enum constant with displayName " + displayName));
    }

    public org.omnione.did.crypto.enums.SymmetricCipherType toOmnioneSymmetricCipherType() {
        switch (this) {
            case AES_128_CBC:
                return org.omnione.did.crypto.enums.SymmetricCipherType.AES_128_CBC;
            case AES_128_ECB:
                return org.omnione.did.crypto.enums.SymmetricCipherType.AES_128_ECB;
            case AES_256_CBC:
                return org.omnione.did.crypto.enums.SymmetricCipherType.AES_256_CBC;
            case AES_256_ECB:
                return org.omnione.did.crypto.enums.SymmetricCipherType.AES_256_ECB;
            default:
                throw new CommonSdkException(ErrorCode.INVALID_SYMMETRIC_CIPHER_TYPE);
        }
    }
}
