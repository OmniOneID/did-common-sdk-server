package org.omnione.did.common.service.impl.vc;

import org.omnione.did.common.datamodel.data.vc.CredentialSubject;
import org.omnione.did.common.datamodel.data.vc.IssueProfile;
import org.omnione.did.common.exception.ErrorCode;
import org.omnione.did.common.exception.ErrorCode;
import org.omnione.did.common.exception.CommonSdkException;
import org.omnione.did.common.service.vc.VcService;
import org.omnione.did.common.util.vc.BaseCoreVcUtil;
import org.omnione.did.core.manager.VcManager;
import org.omnione.did.data.model.did.DidDocument;
import org.omnione.did.data.model.vc.VerifiableCredential;

/**
 * BaseVcServiceImpl.
 * Abstract implementation of VcService defined in OpenDID Common SDK SoT v1.7.
 */
public abstract class BaseVcServiceImpl implements VcService {

        @Override
        public VerifiableCredential issueCredential(
                        IssueProfile issueProfile,
                        CredentialSubject credentialSubject) throws CommonSdkException {
                // 1. VC 생성
                VcManager vcManager = createVcManager(issueProfile, credentialSubject);

                // 2. VC 서명
                VerifiableCredential vc = signVerifiableCredential(vcManager);

                // 3. VC 저장
                saveVerifiableCredential(vc);

                return vc;
        }

        @Override
        public void verifyCredential(
                        VerifiableCredential vc,
                        DidDocument issuerDidDoc) throws CommonSdkException {
                // 1. VC 구조 검증
                BaseCoreVcUtil.validateVerifiableCredential(vc);

                // 2. 서명 검증
                verifyProof(vc, vc.getProof(), issuerDidDoc);

                // 3. 만료 검증
                validateExpiration(getExpirationDate(vc));

                // 4. 폐기 여부 확인
                checkRevocationStatus(vc.getId());
        }

        // 서버별 구현 필요
        protected abstract VcManager createVcManager(
                        IssueProfile issueProfile,
                        CredentialSubject credentialSubject) throws CommonSdkException;

        protected abstract VerifiableCredential signVerifiableCredential(
                        VcManager vcManager) throws CommonSdkException;

        protected abstract void saveVerifiableCredential(
                        VerifiableCredential vc) throws CommonSdkException;

        protected abstract void checkRevocationStatus(String vcId) throws CommonSdkException;

        /**
         * Verifies the proof of a Verifiable Credential.
         * This method is added to support the SoT logic which calls verifyProof from
         * within this class.
         *
         * @param data         The data that was signed (the VC).
         * @param proof        The proof object attached to the VC.
         * @param issuerDidDoc The DID document of the issuer.
         * @throws CommonSdkException if verification fails.
         */
        protected void verifyProof(Object data, Object proof, DidDocument issuerDidDoc) throws CommonSdkException {
                if (data == null || proof == null || issuerDidDoc == null) {
                        throw new CommonSdkException(ErrorCode.INVALID_PARAMETER,
                                        "Data, Proof, and Issuer DID Document are required.");
                }

                try {
                        // 1. Extract Signature Message
                        byte[] signatureMessage = org.omnione.did.common.domain.signature.SignatureCryptoHelper
                                        .extractSignatureMessage(data);

                        // 2. Extract Proof details via Reflection
                        String proofValue = null;
                        String verificationMethod = null;
                        String proofTypeStr = null;

                        java.lang.reflect.Method getProofValue = proof.getClass().getMethod("getProofValue");
                        proofValue = (String) getProofValue.invoke(proof);

                        java.lang.reflect.Method getVerificationMethod = proof.getClass()
                                        .getMethod("getVerificationMethod");
                        verificationMethod = (String) getVerificationMethod.invoke(proof);

                        java.lang.reflect.Method getType = proof.getClass().getMethod("getType");
                        Object typeObj = getType.invoke(proof);
                        proofTypeStr = typeObj.toString();

                        if (proofValue == null || verificationMethod == null || proofTypeStr == null) {
                                throw new CommonSdkException(ErrorCode.INVALID_SIGNATURE,
                                                "Invalid Proof object: missing required fields.");
                        }

                        // 3. Get Public Key
                        String publicKey = org.omnione.did.common.util.did.BaseCoreDidUtil.getPublicKey(issuerDidDoc,
                                        verificationMethod);

                        // 4. Verify Signature
                        org.omnione.did.common.datamodel.enums.did.ProofType proofType = org.omnione.did.common.datamodel.enums.did.ProofType
                                        .valueOf(proofTypeStr);

                        org.omnione.did.common.domain.signature.SignatureCryptoHelper.verifySignature(
                                        publicKey,
                                        proofValue,
                                        signatureMessage,
                                        proofType);

                } catch (CommonSdkException e) {
                        throw e;
                } catch (Exception e) {
                        throw new CommonSdkException(ErrorCode.SIGNATURE_VERIFICATION_FAILED,
                                        "Failed to verify credential proof: " + e.getMessage());
                }
        }

        /**
         * Helper method to get expiration date using reflection.
         */
        private Object getExpirationDate(VerifiableCredential vc) {
                try {
                        // Try to find a method that returns expiration date
                        java.lang.reflect.Method[] methods = vc.getClass().getMethods();
                        for (java.lang.reflect.Method m : methods) {
                                if (m.getName().equalsIgnoreCase("getExpirationDate")
                                                || m.getName().equalsIgnoreCase("getValidUntil")) {
                                        return m.invoke(vc);
                                }
                        }
                        return null;
                } catch (Exception e) {
                        return null;
                }
        }

        /**
         * Validates the expiration date of a Verifiable Credential.
         *
         * @param expirationDate The expiration date object (typically a String in
         *                       standard formats).
         * @throws CommonSdkException if the credential has expired.
         */
        protected void validateExpiration(Object expirationDate) throws CommonSdkException {
                if (expirationDate == null) {
                        return; // No expiration date, valid indefinitely
                }

                try {
                        java.time.Instant expiry;
                        if (expirationDate instanceof String) {
                                // Try ISO-8601
                                expiry = java.time.Instant.parse((String) expirationDate);
                        } else {
                                // Fallback or other types -
                                // For now, assume String as per common VC specs.
                                return;
                        }

                        if (expiry.isBefore(java.time.Instant.now())) {
                                throw new CommonSdkException(ErrorCode.VC_VERIFICATION_FAILED,
                                                "Verifiable Credential has expired.");
                        }
                } catch (Exception e) {
                        // If parsing fails, we might want to throw or ignore depending on strictness.
                        // Following 'verify' semantics, invalid date format should probably fail.
                        throw new CommonSdkException(ErrorCode.VC_VERIFICATION_FAILED,
                                        "Invalid expiration date format: " + e.getMessage());
                }
        }
}
