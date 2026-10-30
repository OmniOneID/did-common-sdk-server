package org.omnione.did.common.datamodel.data.did;

import com.google.gson.annotations.SerializedName;
import org.omnione.did.data.model.did.DidDocument;

public class DidDoc {
    @SerializedName("didDoc")
    private DidDocument didDoc;

    public DidDoc() {
    }

    public DidDoc(DidDocument didDoc) {
        this.didDoc = didDoc;
    }

    public DidDocument getDidDoc() {
        return didDoc;
    }

    public void setDidDoc(DidDocument didDoc) {
        this.didDoc = didDoc;
    }

    @Override
    public String toString() {
        return "DidDoc{" +
                "didDoc=" + didDoc +
                '}';
    }
}
