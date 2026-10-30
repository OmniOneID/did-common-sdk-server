package org.omnione.did.common.datamodel.data.e2e;

import com.google.gson.annotations.SerializedName;
import org.omnione.did.common.datamodel.enums.crypto.SymmetricCipherType;
import org.omnione.did.common.datamodel.enums.crypto.SymmetricPaddingType;
import org.omnione.did.common.datamodel.enums.crypto.EccCurveType;
import org.omnione.did.data.model.did.Proof;

public class ReqE2e {
    @SerializedName("nonce")
    private String nonce;

    @SerializedName("curve")
    private EccCurveType curve;

    @SerializedName("publicKey")
    private String publicKey;

    @SerializedName("cipher")
    private SymmetricCipherType cipher;

    @SerializedName("padding")
    private SymmetricPaddingType padding;

    @SerializedName("proof")
    private Proof proof;

    public ReqE2e() {
    }

    public ReqE2e(String nonce, EccCurveType curve, String publicKey, SymmetricCipherType cipher,
            SymmetricPaddingType padding, Proof proof) {
        this.nonce = nonce;
        this.curve = curve;
        this.publicKey = publicKey;
        this.cipher = cipher;
        this.padding = padding;
        this.proof = proof;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public EccCurveType getCurve() {
        return curve;
    }

    public void setCurve(EccCurveType curve) {
        this.curve = curve;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public SymmetricCipherType getCipher() {
        return cipher;
    }

    public void setCipher(SymmetricCipherType cipher) {
        this.cipher = cipher;
    }

    public SymmetricPaddingType getPadding() {
        return padding;
    }

    public void setPadding(SymmetricPaddingType padding) {
        this.padding = padding;
    }

    public Proof getProof() {
        return proof;
    }

    public void setProof(Proof proof) {
        this.proof = proof;
    }

    @Override
    public String toString() {
        return "ReqE2e{" +
                "nonce='" + nonce + '\'' +
                ", curve=" + curve +
                ", publicKey='" + publicKey + '\'' +
                ", cipher=" + cipher +
                ", padding=" + padding +
                ", proof=" + proof +
                '}';
    }
}
