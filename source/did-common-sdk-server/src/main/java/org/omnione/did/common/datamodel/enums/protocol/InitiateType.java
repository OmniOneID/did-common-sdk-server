package org.omnione.did.common.datamodel.enums.protocol;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.gson.annotations.SerializedName;

public enum InitiateType {
    @SerializedName("user_init")
    USER_INIT("user_init"),

    @SerializedName("issuer_init")
    ISSUER_INIT("issuer_init");

    private final String type;

    InitiateType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    @JsonValue
    public String toString() {
        return type;
    }
}
