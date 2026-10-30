package org.omnione.did.common.datamodel.data.vc;

import com.google.gson.annotations.SerializedName;

public class VcProfile {
    @SerializedName("id")
    private String id;

    @SerializedName("credentialSchema")
    private CredentialSchema credentialSchema;

    @SerializedName("issuer")
    private Issuer issuer;

    public VcProfile() {
    }

    public VcProfile(String id, CredentialSchema credentialSchema, Issuer issuer) {
        this.id = id;
        this.credentialSchema = credentialSchema;
        this.issuer = issuer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CredentialSchema getCredentialSchema() {
        return credentialSchema;
    }

    public void setCredentialSchema(CredentialSchema credentialSchema) {
        this.credentialSchema = credentialSchema;
    }

    public Issuer getIssuer() {
        return issuer;
    }

    public void setIssuer(Issuer issuer) {
        this.issuer = issuer;
    }

    @Override
    public String toString() {
        return "VcProfile{" +
                "id='" + id + '\'' +
                ", credentialSchema=" + credentialSchema +
                ", issuer=" + issuer +
                '}';
    }
}
