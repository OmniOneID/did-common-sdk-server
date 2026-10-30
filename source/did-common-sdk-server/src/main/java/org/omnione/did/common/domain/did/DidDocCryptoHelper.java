package org.omnione.did.common.domain.did;

import org.omnione.did.data.model.did.DidDocument;
import org.omnione.did.data.model.did.InvokedDidDoc;
import org.omnione.did.data.model.did.Proof;
import org.omnione.did.data.model.provider.Provider;
import org.omnione.did.common.datamodel.enums.did.ProofPurpose;
import org.omnione.did.common.datamodel.enums.did.ProofType;
import org.omnione.did.common.exception.ErrorCode;
import org.omnione.did.common.exception.CommonSdkException;
import org.omnione.did.common.util.DateTimeUtil;
import org.omnione.did.common.util.crypto.BaseCryptoUtil;
import org.omnione.did.common.util.did.BaseCoreDidUtil;
import org.omnione.did.common.util.digest.BaseDigestUtil;
import org.omnione.did.common.util.multibase.BaseMultibaseUtil;
import org.omnione.did.common.util.JsonUtil;
import org.omnione.did.wallet.key.WalletManagerInterface;

import java.nio.charset.StandardCharsets;

/**
 * Domain Logic for DID Document Crypto operations.
 */
public class DidDocCryptoHelper {

    /**
     * Verify DID Document signature.
     *
     * @param didDocument          DidDocument to verify
     * @param verificationMethodId Verification Method ID to use
     * @throws CommonSdkException if verification fails
     */
    public static void verifyDidDocumentSignature(
            DidDocument didDocument,
            String verificationMethodId) throws CommonSdkException {
        // 1. Validation
        if (didDocument == null || verificationMethodId == null || verificationMethodId.isBlank()) {
            throw new CommonSdkException(ErrorCode.INVALID_PARAMETER);
        }

        try {
            // 2. Pre-check: Verify if verificationMethodId exists in proofs
            boolean isMethodFound = false;
            // Assuming we check the proof list for the matching verification method
            java.util.List<Proof> proofs = didDocument.getProofs();
            if (proofs != null) {
                for (Proof proof : proofs) {
                    String method = proof.getVerificationMethod();
                    if (method != null
                            && (method.equals(verificationMethodId) || method.endsWith("#" + verificationMethodId))) {
                        isMethodFound = true;
                        break;
                    }
                }
            }

            if (!isMethodFound) {
                throw new CommonSdkException(ErrorCode.DID_DOC_VERIFICATION_METHOD_NOT_FOUND);
            }

            // 3. Verify Signature (Delegate to Core SDK via BaseCoreDidUtil)
            // BaseCoreDidUtil verifies all proofs in the document.
            BaseCoreDidUtil.verifyDidDocKeyProofs(didDocument);

        } catch (CommonSdkException e) {
            throw e;
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.DID_DOCUMENT_VERIFICATION_FAILED,
                    "Failed to verify DID Document signature: " + e.getMessage());
        }
    }

    /**
     * Generate InvokedDidDoc.
     *
     * @param invokerDidDoc  Invoker DID Document
     * @param ownerDidDoc    Owner DID Document (Target)
     * @param proofType      Proof Type (Signature Algorithm)
     * @param certificateUrl Certificate URL (optional)
     * @return InvokedDidDoc object
     * @throws CommonSdkException if generation fails
     */
    public static InvokedDidDoc generateInvokedDidDoc(
            DidDocument invokerDidDoc,
            DidDocument ownerDidDoc,
            ProofType proofType,
            String certificateUrl) throws CommonSdkException {
        // 1. Validation
        if (invokerDidDoc == null || ownerDidDoc == null || proofType == null) {
            throw new CommonSdkException(ErrorCode.INVALID_PARAMETER);
        }

        try {
            // 2. Provider creation
            Provider provider = new Provider();
            provider.setDid(invokerDidDoc.getId());
            provider.setCertVcRef(certificateUrl);

            // 3. Nonce generation
            String nonce = BaseMultibaseUtil.encode(BaseCryptoUtil.generateNonce(16));

            // 4. Verification Method selection
            String verificationMethodIdRaw;
            java.util.List<String> capabilityInvocations = invokerDidDoc.getCapabilityInvocation();
            if (capabilityInvocations != null && !capabilityInvocations.isEmpty()) {
                String methodRef = capabilityInvocations.get(0);
                // Extract fragment if present
                if (methodRef.contains("#")) {
                    verificationMethodIdRaw = methodRef.substring(methodRef.lastIndexOf("#") + 1);
                } else {
                    verificationMethodIdRaw = methodRef;
                }
            } else {
                verificationMethodIdRaw = ProofPurpose.CAPABILITY_INVOCATION.toString();
            }

            // Validate existence
            BaseCoreDidUtil.getVerificationMethod(invokerDidDoc, verificationMethodIdRaw);

            // Construct full verification method ID: did + ?versionId=... + #keyId
            String verificationMethod = invokerDidDoc.getId() + "?versionId=" + invokerDidDoc.getVersionId() + "#"
                    + verificationMethodIdRaw;

            // 5. Proof creation
            Proof proof = new Proof();
            proof.setProofPurpose(ProofPurpose.CAPABILITY_INVOCATION.toString());
            proof.setCreated(DateTimeUtil.getCurrentUTCTimeString());
            proof.setType(proofType.toString());
            proof.setVerificationMethod(verificationMethod);

            // 6. Encode Target DidDoc (Owner)
            String encodedDidDocJson = BaseMultibaseUtil.encode(ownerDidDoc.toJson().getBytes(StandardCharsets.UTF_8));

            // 7. Return InvokedDidDoc
            return new InvokedDidDoc(encodedDidDocJson, proof, provider, nonce);

        } catch (CommonSdkException e) {
            throw e;
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.INVOKED_DOCUMENT_GENERATION_FAILED);
        }
    }

    /**
     * Sign InvokedDidDoc.
     *
     * @param unsignedInvokedDidDoc Unsigned InvokedDidDoc
     * @param walletManager         Wallet Manager
     * @param keyId                 Key ID to sign with
     * @return Signed InvokedDidDoc
     * @throws CommonSdkException if signing fails
     */
    /**
     * Sign InvokedDidDoc.
     *
     * @param unsignedInvokedDidDoc Unsigned InvokedDidDoc
     * @param walletManager         Wallet Manager
     * @param keyId                 Key ID to sign with
     * @return Signed InvokedDidDoc
     * @throws CommonSdkException if signing fails
     */
    public static InvokedDidDoc signInvokedDidDoc(
            InvokedDidDoc unsignedInvokedDidDoc,
            WalletManagerInterface walletManager,
            String keyId) throws CommonSdkException {
        // 1. Validation
        if (unsignedInvokedDidDoc == null || walletManager == null || keyId == null || keyId.isBlank()) {
            throw new CommonSdkException(ErrorCode.INVALID_PARAMETER);
        }

        try {
            // 2. Generate Signature Message (Serialize and Sort)
            String signatureMessage = JsonUtil.serializeAndSort(unsignedInvokedDidDoc);

            // 3. Sign
            byte[] signature = walletManager.generateCompactSignatureFromHash(keyId,
                    BaseDigestUtil.generateHash(signatureMessage.getBytes(StandardCharsets.UTF_8)));

            // 4. Update Proof Value
            Proof proof = new Proof();
            proof.setProofPurpose(unsignedInvokedDidDoc.getProof().getProofPurpose());
            proof.setCreated(unsignedInvokedDidDoc.getProof().getCreated());
            proof.setType(unsignedInvokedDidDoc.getProof().getType());
            proof.setVerificationMethod(unsignedInvokedDidDoc.getProof().getVerificationMethod());
            proof.setProofValue(BaseMultibaseUtil.encode(signature));

            // 5. Return Signed InvokedDidDoc
            return new InvokedDidDoc(unsignedInvokedDidDoc.getDidDoc(), proof, unsignedInvokedDidDoc.getController(),
                    unsignedInvokedDidDoc.getNonce());

        } catch (CommonSdkException e) {
            throw e;
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.SIGNATURE_GENERATION_FAILED);
        }
    }
}
