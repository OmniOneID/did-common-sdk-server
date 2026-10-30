package org.omnione.did.common.datamodel.data.ecdh;

import com.google.gson.annotations.SerializedName;
import org.omnione.did.common.datamodel.enums.crypto.EccCurveType;
import org.omnione.did.data.model.did.Proof;

public class EcdhReqData {
    @SerializedName("client")
    private String client;

    @SerializedName("clientNonce")
    private String clientNonce;

    @SerializedName("curve")
    private EccCurveType curve;

    @SerializedName("publicKey")
    private String publicKey;

    @SerializedName("candidate")
    private Candidate candidate;

    @SerializedName("proof")
    private Proof proof;

    public EcdhReqData() {
    }

    public EcdhReqData(String client, String clientNonce, EccCurveType curve, String publicKey, Candidate candidate,
            Proof proof) {
        this.client = client;
        this.clientNonce = clientNonce;
        this.curve = curve;
        this.publicKey = publicKey;
        this.candidate = candidate;
        this.proof = proof;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getClientNonce() {
        return clientNonce;
    }

    public void setClientNonce(String clientNonce) {
        this.clientNonce = clientNonce;
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

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Proof getProof() {
        return proof;
    }

    public void setProof(Proof proof) {
        this.proof = proof;
    }

    @Override
    public String toString() {
        return "EcdhReqData{" +
                "client='" + client + '\'' +
                ", clientNonce='" + clientNonce + '\'' +
                ", curve=" + curve +
                ", publicKey='" + publicKey + '\'' +
                ", candidate=" + candidate +
                ", proof=" + proof +
                '}';
    }
}
