package org.omnione.did.common.datamodel.enums;

import com.google.gson.annotations.SerializedName;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Language {
    @SerializedName("ko")
    KO("ko"),
    @SerializedName("fr")
    FR("fr"),
    @SerializedName("jp")
    JP("jp"),
    @SerializedName("en")
    EN("en");

    private final String displayName;

    Language(String displayName) {
        this.displayName = displayName;
    }

    @Override
    @JsonValue
    public String toString() {
        return displayName;
    }
}
