package org.omnione.did.common.service.vc;

import org.omnione.did.data.model.did.DidDocument;
import org.omnione.did.common.datamodel.data.vc.IssueProfile;
import org.omnione.did.common.datamodel.data.vc.CredentialSubject;
import org.omnione.did.common.exception.CommonSdkException;
import org.omnione.did.data.model.vc.VerifiableCredential;

/**
 * VcService Interface.
 * Defined in OpenDID Common SDK SoT v1.7.
 */
public interface VcService {
        /**
         * VC 발급
         */
        VerifiableCredential issueCredential(
                        IssueProfile issueProfile,
                        CredentialSubject credentialSubject) throws CommonSdkException;

        /**
         * VC 검증
         */
        void verifyCredential(
                        VerifiableCredential vc,
                        DidDocument issuerDidDoc) throws CommonSdkException;

        /**
         * VC 폐기
         */
        void revokeCredential(
                        String vcId,
                        String reason) throws CommonSdkException;
}
