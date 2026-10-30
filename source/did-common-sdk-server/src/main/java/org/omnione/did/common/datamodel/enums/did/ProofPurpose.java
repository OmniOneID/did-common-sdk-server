package org.omnione.did.common.datamodel.enums.did;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.gson.annotations.SerializedName;

public enum ProofPurpose {
    @SerializedName("assertionMethod")
    ASSERTION_METHOD("assertionMethod"),
    @SerializedName("authentication")
    AUTHENTICATION("authentication"),
    @SerializedName("keyAgreement")
    KEY_AGREEMENT("keyAgreement"),
    @SerializedName("capabilityInvocation")
    CAPABILITY_INVOCATION("capabilityInvocation"),
    @SerializedName("capabilityDelegation")
    CAPABILITY_DELEGATION("capabilityDelegation");

    private final String displayName;

    ProofPurpose(String displayName) {
        this.displayName = displayName;
    }

    @Override
    @JsonValue
    public String toString() {
        return displayName;
    }

    public String toKeyId() {
        switch (this) {
            case ASSERTION_METHOD:
                return "assert";
            case AUTHENTICATION:
                return "auth";
            case KEY_AGREEMENT:
                return "keyagree";
            case CAPABILITY_INVOCATION:
                return "invoke";
            default:
                throw new RuntimeException("Invalid ProofPurpose");
        }
    }
}
