package org.omnione.did.common.datamodel.enums.crypto;

import com.google.gson.annotations.SerializedName;
import com.fasterxml.jackson.annotation.JsonValue;
import org.omnione.did.common.exception.CommonSdkException;
import org.omnione.did.common.exception.ErrorCode;
import java.util.Arrays;

public enum SymmetricPaddingType {
    @SerializedName("NOPAD")
    NOPAD("NOPAD"),
    @SerializedName("PKCS5")
    PKCS5("PKCS5");

    private final String displayName;

    SymmetricPaddingType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    @JsonValue
    public String toString() {
        return displayName;
    }

    public static SymmetricPaddingType fromDisplayName(String displayName) {
        return Arrays.stream(SymmetricPaddingType.values())
                .filter(type -> type.displayName.equals(displayName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No enum constant with displayName " + displayName));
    }

    public org.omnione.did.crypto.enums.SymmetricPaddingType toOmnioneSymmetricPaddingType() {
        switch (this) {
            case NOPAD:
                return org.omnione.did.crypto.enums.SymmetricPaddingType.NOPAD;
            case PKCS5:
                return org.omnione.did.crypto.enums.SymmetricPaddingType.PKCS5;
            default:
                throw new CommonSdkException(ErrorCode.INVALID_SYMMETRIC_PADDING_TYPE);
        }
    }
}
