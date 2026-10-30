package org.omnione.did.common.datamodel.data.e2e;

import com.google.gson.annotations.SerializedName;
import org.omnione.did.data.model.did.Proof;

public class AccE2e {
    @SerializedName("publicKey")
    private String publicKey;

    @SerializedName("iv")
    private String iv;

    @SerializedName("proof")
    private Proof proof;

    public AccE2e() {
    }

    public AccE2e(String publicKey, String iv, Proof proof) {
        this.publicKey = publicKey;
        this.iv = iv;
        this.proof = proof;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public Proof getProof() {
        return proof;
    }

    public void setProof(Proof proof) {
        this.proof = proof;
    }

    @Override
    public String toString() {
        return "AccE2e{" +
                "publicKey='" + publicKey + '\'' +
                ", iv='" + iv + '\'' +
                ", proof=" + proof +
                '}';
    }
}
