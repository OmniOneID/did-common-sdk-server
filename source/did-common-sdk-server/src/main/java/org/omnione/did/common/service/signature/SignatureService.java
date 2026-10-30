package org.omnione.did.common.service.signature;

import org.omnione.did.data.model.did.DidDocument;
import org.omnione.did.common.datamodel.data.signature.Proof;
import org.omnione.did.common.datamodel.enums.did.ProofPurpose;
import org.omnione.did.common.datamodel.enums.did.ProofType;
import org.omnione.did.common.exception.CommonSdkException;

public interface SignatureService {
    Proof generateProof(
            Object data,
            ProofPurpose proofPurpose,
            ProofType proofType,
            String verificationMethod) throws CommonSdkException;

    void verifyProof(
            Object data,
            Proof proof,
            DidDocument didDocument) throws CommonSdkException;

    void verifyDidDocumentProof(
            DidDocument didDocument) throws CommonSdkException;
}
