package org.omnione.did.common.datamodel.enums.did;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.gson.annotations.SerializedName;
import org.omnione.did.common.datamodel.enums.crypto.EccCurveType;

public enum ProofType {
    @SerializedName("RsaSignature2018")
    RSA_SIGNATURE_2018("RsaSignature2018"),
    @SerializedName("Secp256k1Signature2018")
    SECP_256K1_SIGNATURE_2018("Secp256k1Signature2018"),
    @SerializedName("Secp256r1Signature2018")
    SECP_256R1_SIGNATURE_2018("Secp256r1Signature2018");

    private final String displayName;

    ProofType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    @JsonValue
    public String toString() {
        return displayName;
    }

    public EccCurveType toEccCurveType() {
        switch (this) {
            case SECP_256K1_SIGNATURE_2018:
                return EccCurveType.SECP256K1;
            case SECP_256R1_SIGNATURE_2018:
                return EccCurveType.SECP256R1;
            default:
                throw new RuntimeException("Invalid ProofType: " + this);
        }
    }

    public static ProofType fromDisplayName(String displayName) {
        for (ProofType type : ProofType.values()) {
            if (type.displayName.equals(displayName)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant with displayName " + displayName);
    }
}
