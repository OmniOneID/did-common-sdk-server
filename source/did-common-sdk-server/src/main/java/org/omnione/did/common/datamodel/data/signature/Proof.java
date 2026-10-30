package org.omnione.did.common.datamodel.data.signature;

import org.omnione.did.common.datamodel.enums.did.ProofPurpose;
import org.omnione.did.common.datamodel.enums.did.ProofType;

public class Proof {
    private ProofType type;
    private String created;
    private String verificationMethod;
    private ProofPurpose proofPurpose;
    private String proofValue;

    public Proof() {
    }

    public Proof(ProofType type, String created, String verificationMethod, ProofPurpose proofPurpose,
            String proofValue) {
        this.type = type;
        this.created = created;
        this.verificationMethod = verificationMethod;
        this.proofPurpose = proofPurpose;
        this.proofValue = proofValue;
    }

    public ProofType getType() {
        return type;
    }

    public void setType(ProofType type) {
        this.type = type;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getVerificationMethod() {
        return verificationMethod;
    }

    public void setVerificationMethod(String verificationMethod) {
        this.verificationMethod = verificationMethod;
    }

    public ProofPurpose getProofPurpose() {
        return proofPurpose;
    }

    public void setProofPurpose(ProofPurpose proofPurpose) {
        this.proofPurpose = proofPurpose;
    }

    public String getProofValue() {
        return proofValue;
    }

    public void setProofValue(String proofValue) {
        this.proofValue = proofValue;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ProofType type;
        private String created;
        private String verificationMethod;
        private ProofPurpose proofPurpose;
        private String proofValue;

        public Builder type(ProofType type) {
            this.type = type;
            return this;
        }

        public Builder created(String created) {
            this.created = created;
            return this;
        }

        public Builder verificationMethod(String verificationMethod) {
            this.verificationMethod = verificationMethod;
            return this;
        }

        public Builder proofPurpose(ProofPurpose proofPurpose) {
            this.proofPurpose = proofPurpose;
            return this;
        }

        public Builder proofValue(String proofValue) {
            this.proofValue = proofValue;
            return this;
        }

        public Proof build() {
            return new Proof(type, created, verificationMethod, proofPurpose, proofValue);
        }
    }
}
