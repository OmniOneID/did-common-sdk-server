package org.omnione.did.common.datamodel.enums;

import com.google.gson.annotations.SerializedName;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Encoding {
    @SerializedName("UTF-8")
    UTF_8("UTF-8"),
    @SerializedName("UTF-16")
    UTF_16("UTF-16"),
    @SerializedName("EUC-KR")
    EUC_KR("EUC-KR"),
    @SerializedName("ASCII")
    ASCII("ASCII");

    private final String displayName;

    Encoding(String displayName) {
        this.displayName = displayName;
    }

    @Override
    @JsonValue
    public String toString() {
        return displayName;
    }
}
