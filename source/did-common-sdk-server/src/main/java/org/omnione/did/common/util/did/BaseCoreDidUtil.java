package org.omnione.did.common.util.did;

import org.bouncycastle.util.encoders.Hex;
import org.omnione.did.common.exception.ErrorCode;
import org.omnione.did.common.exception.CommonSdkException;
import org.omnione.did.core.data.rest.DidKeyInfo;
import org.omnione.did.core.data.rest.SignatureParams;
import org.omnione.did.core.manager.DidManager;
import org.omnione.did.data.model.did.DidDocument;
import org.omnione.did.data.model.did.VerificationMethod;
import org.omnione.did.wallet.key.WalletManagerInterface;
import org.omnione.did.data.model.enums.did.ProofPurpose;
import org.omnione.did.data.model.enums.did.DidKeyType;
import org.omnione.did.data.model.enums.did.AuthType;
import org.omnione.did.wallet.exception.WalletException;
import org.omnione.did.wallet.key.data.KeyElement;
import org.omnione.did.crypto.util.MultiBaseUtils;
import org.omnione.did.crypto.enums.MultiBaseType;
import org.omnione.did.crypto.enums.DigestType;
import org.omnione.did.crypto.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;

/**
 * OpenDID Common SDK BaseCoreDidUtil Class.
 */
public class BaseCoreDidUtil {

    /**
     * Parse DID Document JSON string.
     *
     * @param didDocJson DID Document JSON string
     * @return DidManager instance
     * @throws CommonSdkException if parsing fails
     */
    public static DidManager parseDidDoc(String didDocJson) throws CommonSdkException {
        try {
            DidManager didManager = new DidManager();
            didManager.parse(didDocJson);
            return didManager;
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.INVALID_DID_DOCUMENT,
                    "Failed to parse DID Document JSON: " + e.getMessage());
        }
    }

    /**
     * Parse DID Document object.
     *
     * @param didDocument DidDocument object
     * @return DidManager instance
     * @throws CommonSdkException if parsing fails
     */
    public static DidManager parseDidDoc(DidDocument didDocument) throws CommonSdkException {
        try {
            DidManager didManager = new DidManager();
            didManager.parse(didDocument.toJson());
            return didManager;
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.INVALID_DID_DOCUMENT,
                    "Failed to parse DID Document object: " + e.getMessage());
        }
    }

    /**
     * Get verification method by key ID.
     *
     * @param didManager DidManager instance
     * @param keyId      Key ID (verification method ID)
     * @return VerificationMethod object
     * @throws CommonSdkException if verification method not found
     */
    public static VerificationMethod getVerificationMethod(DidManager didManager, String keyId)
            throws CommonSdkException {
        try {
            VerificationMethod method = didManager.getVerificationMethodByKeyId(keyId);
            if (method == null) {
                throw new CommonSdkException(ErrorCode.DID_DOCUMENT_VERIFICATION_FAILED,
                        "Verification method not found for keyId: " + keyId);
            }
            return method;
        } catch (CommonSdkException e) {
            throw e;
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.DID_DOCUMENT_VERIFICATION_FAILED,
                    "Failed to get verification method: " + e.getMessage());
        }
    }

    /**
     * Get verification method by key ID from DidDocument.
     *
     * @param didDocument DidDocument object
     * @param keyId       Key ID
     * @return VerificationMethod object
     * @throws CommonSdkException if verification method not found
     */
    public static VerificationMethod getVerificationMethod(DidDocument didDocument, String keyId)
            throws CommonSdkException {
        try {
            List<VerificationMethod> methods = didDocument.getVerificationMethod();
            if (methods != null) {
                for (VerificationMethod method : methods) {
                    if (method.getId().equals(keyId) || method.getId().endsWith("#" + keyId)) {
                        return method;
                    }
                }
            }
            throw new CommonSdkException(ErrorCode.DID_DOCUMENT_VERIFICATION_FAILED,
                    "Verification method not found for keyId: " + keyId);
        } catch (CommonSdkException e) {
            throw e;
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.DID_DOCUMENT_VERIFICATION_FAILED,
                    "Failed to search verification method: " + e.getMessage());
        }
    }

    /**
     * Get public key from DID Document.
     *
     * @param didDocument DidDocument object
     * @param keyId       Key ID
     * @return Public key string (Multibase or other format as stored)
     * @throws CommonSdkException if key not found
     */
    public static String getPublicKey(DidDocument didDocument, String keyId) throws CommonSdkException {
        try {
            VerificationMethod method = getVerificationMethod(didDocument, keyId);
            if (method.getPublicKeyMultibase() != null) {
                return method.getPublicKeyMultibase();
            }
            throw new CommonSdkException(ErrorCode.DID_DOCUMENT_VERIFICATION_FAILED,
                    "Public key not found in verification method.");
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.DID_DOCUMENT_VERIFICATION_FAILED,
                    "Failed to get public key: " + e.getMessage());
        }
    }

    /**
     * Verify DID Document key proofs.
     *
     * @param didDocument DidDocument to verify
     * @throws CommonSdkException if verification fails
     */
    public static void verifyDidDocKeyProofs(DidDocument didDocument) throws CommonSdkException {
        try {
            DidManager didManager = new DidManager();
            didManager.parse(didDocument.toJson());
            didManager.verifyDocumentSignature();
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.DID_DOCUMENT_VERIFICATION_FAILED,
                    "Failed to verify DID Document proofs: " + e.getMessage());
        }
    }

    /**
     * Create a new DID Document.
     *
     * @param didManager  DidManager instance
     * @param did         DID string
     * @param controller  Controller string
     * @param didKeyInfos List of DID Key Info
     * @return Created DidDocument
     * @throws CommonSdkException if creation fails
     */
    public static DidDocument createDidDocument(DidManager didManager, String did, String controller,
            List<DidKeyInfo> didKeyInfos) throws CommonSdkException {
        try {
            return didManager.createDocument(did, controller, didKeyInfos);
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.INVALID_DID_DOCUMENT,
                    "Failed to create DID Document: " + e.getMessage());
        }
    }

    public static String setDidKeyType(String algo) {
        switch (algo) {
            case "Rsa2048":
                return DidKeyType.RSA_VERIFICATION_KEY_2018.getRawValue();
            case "Secp256r1":
                return DidKeyType.SECP256R1_VERIFICATION_KEY_2018.getRawValue();
            case "Secp256k1":
                return DidKeyType.SECP256K1_VERIFICATION_KEY_2018.getRawValue();
            default:
                return null;
        }
    }

    public static Map<String, List<ProofPurpose>> createDefaultProofPurposes() {
        Map<String, List<ProofPurpose>> purposes = new LinkedHashMap<>();
        purposes.put("auth", List.of(ProofPurpose.AUTHENTICATION));
        purposes.put("assert", List.of(ProofPurpose.ASSERTION_METHOD));
        purposes.put("keyagree", List.of(ProofPurpose.KEY_AGREEMENT));
        purposes.put("invoke", List.of(ProofPurpose.CAPABILITY_INVOCATION));
        return purposes;
    }

    public static List<DidKeyInfo> getDidKeyInfosFromWallet(WalletManagerInterface walletManager, String controller,
            Map<String, List<ProofPurpose>> purposes) {
        List<DidKeyInfo> didKeyInfos = new ArrayList<>();
        try {
            for (String keyId : walletManager.getKeyIdList()) {
                KeyElement keyElement = walletManager.getKeyElement(keyId);
                DidKeyInfo keyInfo = new DidKeyInfo();
                keyInfo.setKeyId(keyId);
                keyInfo.setAlgoType(setDidKeyType(keyElement.getAlgorithm()));
                keyInfo.setPublicKey(keyElement.getPublicKey());
                keyInfo.setController(controller);
                keyInfo.setAuthType(AuthType.Free);
                keyInfo.setKeyPurpose(purposes.getOrDefault(keyId, new ArrayList<>()));
                didKeyInfos.add(keyInfo);
            }
        } catch (WalletException e) {
            // log.error("Failed to get key element: {}", e.getMessage()); // log not
            // available in static util without lombok
            throw new CommonSdkException(ErrorCode.FAILED_TO_LOAD_KEY_ELEMENT);
        }
        return didKeyInfos;
    }

    public static List<String> getSigningKeyIds(Map<String, List<ProofPurpose>> purposes) {
        List<String> keyIds = new ArrayList<>();
        for (Map.Entry<String, List<ProofPurpose>> entry : purposes.entrySet()) {
            for (ProofPurpose purpose : entry.getValue()) {
                if (purpose != ProofPurpose.KEY_AGREEMENT) {
                    keyIds.add(entry.getKey());
                    break;
                }
            }
        }
        return keyIds;
    }

    /**
     * Parse DID identifier from DID Document JSON string.
     *
     * @param didDocJson DID Document JSON string
     * @return DID identifier (the "id" field)
     * @throws CommonSdkException if parsing fails
     */
    public static String parseDid(String didDocJson) throws CommonSdkException {
        try {
            DidDocument didDocument = new DidDocument();
            didDocument.fromJson(didDocJson);
            return didDocument.getId();
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.INVALID_DID_DOCUMENT,
                    "Failed to parse DID from DID Document JSON: " + e.getMessage());
        }
    }

    /**
     * Sign DID Document and add proof.
     *
     * @param didManager    DidManager instance containing the doc
     * @param walletManager WalletManager for signing
     * @param signingKeyIds List of key IDs to sign with
     * @return Signed DidDocument
     * @throws CommonSdkException if encryption/signing fails
     */
    public static DidDocument signAndAddProof(DidManager didManager, WalletManagerInterface walletManager,
            List<String> signingKeyIds) throws CommonSdkException {
        try {
            List<SignatureParams> signatureParamsList = didManager.getOriginDataForSign(signingKeyIds);

            for (SignatureParams params : signatureParamsList) {
                String keyId = params.getKeyId();
                String originData = params.getOriginData();

                byte[] dataToSign = originData.getBytes(StandardCharsets.UTF_8);
                // Use DigestUtils if available, or MessageDigest
                byte[] hash = MessageDigest.getInstance("SHA-256").digest(dataToSign);

                byte[] signature = walletManager.generateCompactSignatureFromHash(keyId, hash);
                // Use Multibase encoding
                String signatureString = MultiBaseUtils.encode(signature, MultiBaseType.base58btc);

                params.setSignatureValue(signatureString);
            }

            didManager.addProof(signatureParamsList);
            return didManager.getDocument();
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.INVALID_SIGNATURE, "Failed to sign DID Document: " + e.getMessage());
        }
    }
}
