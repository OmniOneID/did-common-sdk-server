package org.omnione.did.common.datamodel.data.ecdh;

import com.google.gson.annotations.SerializedName;
import org.omnione.did.common.datamodel.enums.crypto.SymmetricCipherType;
import org.omnione.did.common.datamodel.enums.crypto.SymmetricPaddingType;
import org.omnione.did.data.model.did.Proof;

public class AccEcdh {
    @SerializedName("server")
    private String server;

    @SerializedName("serverNonce")
    private String serverNonce;

    @SerializedName("publicKey")
    private String publicKey;

    @SerializedName("cipher")
    private SymmetricCipherType cipher;

    @SerializedName("padding")
    private SymmetricPaddingType padding;

    @SerializedName("proof")
    private Proof proof;

    public AccEcdh() {
    }

    public AccEcdh(String server, String serverNonce, String publicKey, SymmetricCipherType cipher,
            SymmetricPaddingType padding, Proof proof) {
        this.server = server;
        this.serverNonce = serverNonce;
        this.publicKey = publicKey;
        this.cipher = cipher;
        this.padding = padding;
        this.proof = proof;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getServerNonce() {
        return serverNonce;
    }

    public void setServerNonce(String serverNonce) {
        this.serverNonce = serverNonce;
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
        return "AccEcdh{" +
                "server='" + server + '\'' +
                ", serverNonce='" + serverNonce + '\'' +
                ", publicKey='" + publicKey + '\'' +
                ", cipher=" + cipher +
                ", padding=" + padding +
                ", proof=" + proof +
                '}';
    }
}
