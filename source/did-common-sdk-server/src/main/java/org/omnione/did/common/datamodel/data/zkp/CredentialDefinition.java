package org.omnione.did.common.datamodel.data.zkp;

import com.google.gson.annotations.SerializedName;

public class CredentialDefinition {
    @SerializedName("id")
    private String id;

    @SerializedName("schemaId")
    private String schemaId;

    public CredentialDefinition() {
    }

    public CredentialDefinition(String id, String schemaId) {
        this.id = id;
        this.schemaId = schemaId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchemaId() {
        return schemaId;
    }

    public void setSchemaId(String schemaId) {
        this.schemaId = schemaId;
    }

    @Override
    public String toString() {
        return "CredentialDefinition{" +
                "id='" + id + '\'' +
                ", schemaId='" + schemaId + '\'' +
                '}';
    }
}
