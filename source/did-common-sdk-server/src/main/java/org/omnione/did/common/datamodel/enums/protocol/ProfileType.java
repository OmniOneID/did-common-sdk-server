package org.omnione.did.common.datamodel.enums.protocol;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.gson.annotations.SerializedName;

public enum ProfileType {
    @SerializedName("IssueProfile")
    ISSUE_PROFILE("IssueProfile"),
    @SerializedName("VerifyProfile")
    VERIFY_PROFILE("VerifyProfile");

    private final String displayName;

    ProfileType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    @JsonValue
    public String toString() {
        return displayName;
    }
}
