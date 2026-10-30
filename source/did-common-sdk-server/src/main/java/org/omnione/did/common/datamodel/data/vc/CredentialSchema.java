package org.omnione.did.common.datamodel.data.vc;

import com.google.gson.annotations.SerializedName;
import org.omnione.did.common.datamodel.enums.did.CredentialSchemaType;

public class CredentialSchema {
    @SerializedName("id")
    private String id;

    @SerializedName("type")
    private CredentialSchemaType type;

    @SerializedName("value")
    private String value;

    public CredentialSchema() {
    }

    public CredentialSchema(String id, CredentialSchemaType type, String value) {
        this.id = id;
        this.type = type;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CredentialSchemaType getType() {
        return type;
    }

    public void setType(CredentialSchemaType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CredentialSchema{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}
