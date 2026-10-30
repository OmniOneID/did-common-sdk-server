package org.omnione.did.common.datamodel.data.provider;

import com.google.gson.annotations.SerializedName;

/**
 * Provider information for DID Document attestation.
 *
 * <p>Represents the Wallet Provider or Entity that attested a DID Document.
 * Used in {@link AttestedDidDoc} as part of the P131 Wallet Registration Protocol.</p>
 */
public class Provider {

    @SerializedName("did")
    private String did;

    @SerializedName("certVcRef")
    private String certVcRef;

    public Provider() {
    }

    public Provider(String did, String certVcRef) {
        this.did = did;
        this.certVcRef = certVcRef;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getCertVcRef() {
        return certVcRef;
    }

    public void setCertVcRef(String certVcRef) {
        this.certVcRef = certVcRef;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String did;
        private String certVcRef;

        public Builder did(String did) {
            this.did = did;
            return this;
        }

        public Builder certVcRef(String certVcRef) {
            this.certVcRef = certVcRef;
            return this;
        }

        public Provider build() {
            return new Provider(did, certVcRef);
        }
    }

    @Override
    public String toString() {
        return "Provider{" +
                "did='" + did + '\'' +
                ", certVcRef='" + certVcRef + '\'' +
                '}';
    }
}
