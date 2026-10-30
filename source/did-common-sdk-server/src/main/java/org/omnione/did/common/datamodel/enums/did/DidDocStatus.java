package org.omnione.did.common.datamodel.enums.did;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.gson.annotations.SerializedName;

public enum DidDocStatus {
    @SerializedName("ACTIVATED")
    ACTIVATED("ACTIVATED"),
    @SerializedName("DEACTIVATED")
    DEACTIVATED("DEACTIVATED"),
    @SerializedName("REVOKED")
    REVOKED("REVOKED"),
    @SerializedName("TERMINATED")
    TERMINATED("TERMINATED");

    private final String displayName;

    DidDocStatus(String displayName) {
        this.displayName = displayName;
    }

    @Override
    @JsonValue
    public String toString() {
        return displayName;
    }
}
