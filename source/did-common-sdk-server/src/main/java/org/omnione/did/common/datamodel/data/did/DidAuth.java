package org.omnione.did.common.datamodel.data.did;

import com.google.gson.annotations.SerializedName;
import org.omnione.did.data.model.did.Proof;

public class DidAuth {
    @SerializedName("did")
    private String did;

    @SerializedName("authNonce")
    private String authNonce;

    @SerializedName("proof")
    private Proof proof;

    public DidAuth() {
    }

    public DidAuth(String did, String authNonce, Proof proof) {
        this.did = did;
        this.authNonce = authNonce;
        this.proof = proof;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getAuthNonce() {
        return authNonce;
    }

    public void setAuthNonce(String authNonce) {
        this.authNonce = authNonce;
    }

    public Proof getProof() {
        return proof;
    }

    public void setProof(Proof proof) {
        this.proof = proof;
    }

    @Override
    public String toString() {
        return "DidAuth{" +
                "did='" + did + '\'' +
                ", authNonce='" + authNonce + '\'' +
                ", proof=" + proof +
                '}';
    }
}
