package org.omnione.did.common.datamodel.enums.protocol;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.gson.annotations.SerializedName;

public enum OfferType {
    @SerializedName("IssueOffer")
    ISSUE_VC("IssueOffer"),
    @SerializedName("VerifyOffer")
    VERIFY_VP("VerifyOffer"),
    @SerializedName("RestoreDidOffer")
    RESTORE_DID("RestoreDidOffer");

    private final String value;

    OfferType(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return value;
    }
}
