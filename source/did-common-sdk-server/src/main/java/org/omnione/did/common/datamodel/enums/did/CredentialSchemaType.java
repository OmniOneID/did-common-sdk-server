package org.omnione.did.common.datamodel.enums.did;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.gson.annotations.SerializedName;

public enum CredentialSchemaType {
    @SerializedName("OsdSchemaCredential")
    OSD_SCHEMA_CREDENTIAL("OsdSchemaCredential");

    private final String displayName;

    CredentialSchemaType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    @JsonValue
    public String toString() {
        return displayName;
    }
}
