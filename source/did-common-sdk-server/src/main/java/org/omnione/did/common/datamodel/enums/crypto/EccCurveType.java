package org.omnione.did.common.datamodel.enums.crypto;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.gson.annotations.SerializedName;
import org.omnione.did.common.exception.CommonSdkException;
import org.omnione.did.common.exception.ErrorCode;

public enum EccCurveType {
    @SerializedName("Secp256k1")
    SECP256K1("Secp256k1"),
    @SerializedName("Secp256r1")
    SECP256R1("Secp256r1");

    private final String displayName;

    EccCurveType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    @JsonValue
    public String toString() {
        return displayName;
    }

    public org.omnione.did.crypto.enums.EccCurveType toOmnioneEccCurveType() {
        switch (this) {
            case SECP256K1:
                return org.omnione.did.crypto.enums.EccCurveType.Secp256k1;
            case SECP256R1:
                return org.omnione.did.crypto.enums.EccCurveType.Secp256r1;
            default:
                throw new CommonSdkException(ErrorCode.INVALID_ECC_CURVE_TYPE);
        }
    }

    public org.omnione.did.crypto.enums.DidKeyType toOmnioneDidKeyType() {
        switch (this) {
            case SECP256K1:
                return org.omnione.did.crypto.enums.DidKeyType.SECP256K1_VERIFICATION_KEY_2018;
            case SECP256R1:
                return org.omnione.did.crypto.enums.DidKeyType.SECP256R1_VERIFICATION_KEY_2018;
            default:
                return org.omnione.did.crypto.enums.DidKeyType.RSA_VERIFICATION_KEY_2018;
        }
    }
}
