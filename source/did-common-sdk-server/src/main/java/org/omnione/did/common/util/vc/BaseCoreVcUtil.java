package org.omnione.did.common.util.vc;

import org.omnione.did.common.exception.ErrorCode;
import org.omnione.did.common.exception.CommonSdkException;
import org.omnione.did.core.data.rest.ClaimInfo;
import org.omnione.did.core.data.rest.IssueVcParam;
import org.omnione.did.core.data.rest.SignatureVcParams;
import org.omnione.did.core.exception.CoreException;
import org.omnione.did.core.manager.VcManager;
import org.omnione.did.data.model.did.DidDocument;
import org.omnione.did.data.model.enums.vc.VcType;
import org.omnione.did.data.model.provider.ProviderDetail;
import org.omnione.did.data.model.schema.VcSchema;
import org.omnione.did.data.model.vc.DocumentVerificationEvidence;
import org.omnione.did.data.model.vc.VcMeta;
import org.omnione.did.data.model.vc.VerifiableCredential;
import org.omnione.did.common.util.multibase.BaseMultibaseUtil;
import org.omnione.did.wallet.key.WalletManagerInterface;
import org.omnione.did.common.util.wallet.BaseWalletUtil;

import java.nio.charset.StandardCharsets;

import java.util.HashMap;
import java.util.List;

/**
 * OpenDID Common SDK BaseCoreVcUtil Class.
 * Utility class for core VC operations following TA server implementation
 * pattern.
 */
public class BaseCoreVcUtil {

    /**
     * Parses a VC schema from its JSON representation.
     *
     * @param vcSchemaJson The JSON string representing the VC schema.
     * @return The parsed VC schema object.
     * @throws CommonSdkException if parsing fails
     */
    public static VcSchema parseVcSchema(String vcSchemaJson) throws CommonSdkException {
        try {
            VcSchema vcSchema = new VcSchema();
            vcSchema.fromJson(vcSchemaJson);
            return vcSchema;
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.VC_SCHEMA_PARSE_FAILED,
                    "Failed to parse VC schema: " + e.getMessage());
        }
    }

    /**
     * Sets the issuer information in the VC issuance parameters.
     *
     * @param issueVcParam The VC issuance parameters.
     * @param issuerDid    The DID of the issuer.
     * @param issuerName   The name of the issuer.
     * @param certVcRef    The reference of the certificate VC.
     */
    public static void setIssuer(IssueVcParam issueVcParam, String issuerDid, String issuerName, String certVcRef) {
        ProviderDetail providerDetail = new ProviderDetail();
        providerDetail.setDid(issuerDid);
        providerDetail.setName(issuerName);
        providerDetail.setCertVcRef(certVcRef);

        issueVcParam.setProviderDetail(providerDetail);
    }

    /**
     * Sets the VC schema in the VC issuance parameters.
     *
     * @param issueVcParam The VC issuance parameters.
     * @param vcSchemaJson The JSON string representing the VC schema.
     * @throws CommonSdkException if schema parsing fails
     */
    public static void setVcSchema(IssueVcParam issueVcParam, String vcSchemaJson) throws CommonSdkException {
        VcSchema vcSchema = parseVcSchema(vcSchemaJson);
        issueVcParam.setVcSchema(vcSchema);
    }

    /**
     * Sets the claim information in the VC issuance parameters.
     *
     * @param issueVcParam The VC issuance parameters.
     * @param claimInfoMap The map containing the claim information.
     */
    public static void setClaimInfo(IssueVcParam issueVcParam, HashMap<String, ClaimInfo> claimInfoMap) {
        issueVcParam.setPrivacy(claimInfoMap);
    }

    /**
     * Sets the VC types in the VC issuance parameters.
     *
     * @param issueVcParam The VC issuance parameters.
     * @param vcTypeList   The list of VC types.
     */
    public static void setVcTypes(IssueVcParam issueVcParam, List<VcType> vcTypeList) {
        issueVcParam.setVcType(vcTypeList);
    }

    /**
     * Sets the evidence in the VC issuance parameters.
     *
     * @param issueVcParam The VC issuance parameters.
     * @param evidence     The document verification evidence.
     */
    public static void setEvidence(IssueVcParam issueVcParam, DocumentVerificationEvidence evidence) {
        issueVcParam.setEvidences(List.of(evidence));
    }

    /**
     * Generates a Verifiable Credential (VC).
     *
     * @param issueVcParam The VC issuance parameters.
     * @param did          The DID of the recipient (VC issuance target).
     * @return The generated Verifiable Credential.
     * @throws CommonSdkException if the VC generation fails.
     */
    public static VerifiableCredential generateVc(IssueVcParam issueVcParam, String did) throws CommonSdkException {
        try {
            VcManager vcManager = new VcManager();
            return vcManager.issueCredential(issueVcParam, did);
        } catch (CoreException e) {
            throw new CommonSdkException(ErrorCode.VC_GENERATION_FAILED,
                    "Failed to generate VC: " + e.getMessage());
        }
    }

    /**
     * Generates VC metadata.
     *
     * @param vc        The VC to generate the metadata for.
     * @param certVcRef The URL of the certificate VC.
     * @return The generated VC metadata.
     */
    public static VcMeta generateVcMeta(VerifiableCredential vc, String certVcRef) {
        VcManager vcManager = new VcManager();
        return vcManager.generateVcMetaData(vc, certVcRef);
    }

    /**
     * Extracts the VC signature message.
     *
     * @param tasDidDoc            The DID document of the TAS.
     * @param verifiableCredential The VC to extract the signature message from.
     * @return The extracted VC signature message.
     * @throws CommonSdkException if the VC signature message extraction fails.
     */
    public static List<SignatureVcParams> extractVcSignatureMessage(DidDocument tasDidDoc,
            VerifiableCredential verifiableCredential) throws CommonSdkException {
        try {
            VcManager vcManager = new VcManager();
            return vcManager.getOriginDataForSign("assert", tasDidDoc, verifiableCredential);
        } catch (CoreException e) {
            throw new CommonSdkException(ErrorCode.VC_SIGNATURE_EXTRACTION_FAILED,
                    "Failed to extract VC Signature Message: " + e.getMessage());
        }
    }

    /**
     * Sets the proof in a Verifiable Credential (VC).
     *
     * @param verifiableCredential The VC to set the proof for.
     * @param signatureParamsList  The list of signature parameters.
     * @throws CommonSdkException if the VC proof setting fails.
     */
    public static void setVcProof(VerifiableCredential verifiableCredential,
            List<SignatureVcParams> signatureParamsList) throws CommonSdkException {
        try {
            VcManager vcManager = new VcManager();
            vcManager.addProof(verifiableCredential, signatureParamsList);
        } catch (CoreException e) {
            throw new CommonSdkException(ErrorCode.VC_PROOF_SETTING_FAILED,
                    "Failed to set VC Proof: " + e.getMessage());
        }
    }

    /**
     * Parses VC metadata from its JSON representation.
     *
     * @param vcMetaJson The JSON string representing the VC metadata.
     * @return The parsed VC metadata object.
     * @throws CommonSdkException if parsing fails
     */
    public static VcMeta parseVcMeta(String vcMetaJson) throws CommonSdkException {
        try {
            VcMeta vcMeta = new VcMeta();
            vcMeta.fromJson(vcMetaJson);
            return vcMeta;
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.VC_META_PARSE_FAILED,
                    "Failed to parse VC metadata: " + e.getMessage());
        }
    }

    /**
     * Validates the structure of a Verifiable Credential.
     *
     * @param verifiableCredential The VC to validate.
     * @throws CommonSdkException if the VC is invalid.
     */
    public static void validateVerifiableCredential(VerifiableCredential verifiableCredential) throws CommonSdkException {
        if (verifiableCredential == null) {
            throw new CommonSdkException(ErrorCode.VC_VERIFICATION_FAILED, "VerifiableCredential cannot be null");
        }
        // Additional structural validation can be added here
    }

    /**
     * Verifies a Verifiable Credential.
     *
     * @param verifiableCredential The VC to verify.
     * @param didDocument          The DID document of the issuer.
     * @param isRevocationCheck    Flag indicating whether to perform a revocation
     *                             check.
     * @throws CommonSdkException if the VC verification fails.
     */
    public static void verifyVc(VerifiableCredential verifiableCredential, DidDocument didDocument,
            boolean isRevocationCheck) throws CommonSdkException {
        try {
            if (verifiableCredential == null) {
                throw new CommonSdkException(ErrorCode.VC_VERIFICATION_FAILED,
                        "VerifiableCredential cannot be null");
            }
            if (didDocument == null) {
                throw new CommonSdkException(ErrorCode.INVALID_PARAMETER,
                        "Issuer DID Document is required for signature verification");
            }

            VcManager vcManager = new VcManager();
            vcManager.verifyCredential(verifiableCredential, didDocument, isRevocationCheck);
        } catch (CoreException e) {
            throw new CommonSdkException(ErrorCode.VC_VERIFICATION_FAILED,
                    "Failed to verify VC: " + e.getMessage());
        } catch (CommonSdkException e) {
            throw e;
        }
    }

    /**
     * Signs a Verifiable Credential.
     *
     * @param vcManager     The VC manager.
     * @param walletManager The wallet manager.
     * @param issuerDidDoc  The DID document of the issuer.
     * @param keyId         The key ID to be used for signing.
     * @param vc            The Verifiable Credential to be signed.
     * @return The signed Verifiable Credential.
     * @throws CommonSdkException if the signing fails.
     */
    public static VerifiableCredential signVerifiableCredential(VcManager vcManager,
            WalletManagerInterface walletManager,
            DidDocument issuerDidDoc,
            String keyId,
            VerifiableCredential vc) throws CommonSdkException {
        try {
            List<SignatureVcParams> signatureParamsList = vcManager.getOriginDataForSign(keyId, issuerDidDoc, vc);

            for (SignatureVcParams signatureParam : signatureParamsList) {
                String originData = signatureParam.getOriginData();
                byte[] signatureBytes = BaseWalletUtil.generateCompactSignature(walletManager,
                        signatureParam.getKeyId(),
                        originData.getBytes(StandardCharsets.UTF_8));
                String encodedSignature = BaseMultibaseUtil.encode(signatureBytes);
                signatureParam.setSignatureValue(encodedSignature);
            }

            vcManager.addProof(vc, signatureParamsList);
            return vc;
        } catch (CoreException e) {
            throw new CommonSdkException(ErrorCode.VC_SIGNING_FAILED,
                    "Failed to sign Verifiable Credential: " + e.getMessage());
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.VC_SIGNING_FAILED,
                    "Unexpected error during VC signing: " + e.getMessage());
        }
    }
}
