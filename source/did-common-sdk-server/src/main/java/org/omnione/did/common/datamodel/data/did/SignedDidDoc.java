package org.omnione.did.common.datamodel.data.did;

import com.google.gson.annotations.SerializedName;
import org.omnione.did.data.model.did.DidDocument;
import org.omnione.did.data.model.did.Proof;

public class SignedDidDoc {
    @SerializedName("ownerDidDoc")
    private DidDocument ownerDidDoc;

    @SerializedName("proof")
    private Proof proof;

    public SignedDidDoc() {
    }

    public SignedDidDoc(DidDocument ownerDidDoc, Proof proof) {
        this.ownerDidDoc = ownerDidDoc;
        this.proof = proof;
    }

    public DidDocument getOwnerDidDoc() {
        return ownerDidDoc;
    }

    public void setOwnerDidDoc(DidDocument ownerDidDoc) {
        this.ownerDidDoc = ownerDidDoc;
    }

    public Proof getProof() {
        return proof;
    }

    public void setProof(Proof proof) {
        this.proof = proof;
    }

    @Override
    public String toString() {
        return "SignedDidDoc{" +
                "ownerDidDoc=" + ownerDidDoc +
                ", proof=" + proof +
                '}';
    }
}
