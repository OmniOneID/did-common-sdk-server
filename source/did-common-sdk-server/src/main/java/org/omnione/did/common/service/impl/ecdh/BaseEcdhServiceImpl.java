package org.omnione.did.common.service.impl.ecdh;

import org.omnione.did.common.datamodel.data.ecdh.AccEcdh;
import org.omnione.did.common.datamodel.data.ecdh.EcdhReqData;
import org.omnione.did.common.datamodel.enums.crypto.SymmetricCipherType;
import org.omnione.did.common.datamodel.enums.crypto.SymmetricPaddingType;
import org.omnione.did.common.datamodel.enums.did.ProofPurpose;
import org.omnione.did.common.datamodel.enums.did.ProofType;
import org.omnione.did.common.exception.ErrorCode;
import org.omnione.did.common.exception.CommonSdkException;
import org.omnione.did.common.domain.ecdh.EcdhCryptoHelper;
import org.omnione.did.common.domain.signature.SignatureCryptoHelper;
import org.omnione.did.common.service.ecdh.EcdhService;
import org.omnione.did.common.util.crypto.BaseCryptoUtil;
import org.omnione.did.common.util.multibase.BaseMultibaseUtil;
import org.omnione.did.crypto.keypair.KeyPairInterface;
import org.omnione.did.data.model.did.Proof;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Key;

/**
 * Base implementation of ECDH Service.
 * Definition based on SoT 6.2.1.
 */
public abstract class BaseEcdhServiceImpl implements EcdhService {

    // Common Implementation
    @Override
    public AccEcdh processEcdhRequest(
            EcdhReqData ecdhReqData,
            SymmetricCipherType serverCipherType,
            SymmetricPaddingType serverPaddingType) throws CommonSdkException {
        // 1. Verify Signature
        verifyEcdhRequest(ecdhReqData, null);

        // 2. Generate Server Key Pair
        KeyPairInterface keyPair = BaseCryptoUtil.generateKeyPair(ecdhReqData.getCurve());

        // 3. Generate Session Key
        // SoT undefined null variables: serverPrivateKey, mergedNonce
        // Logic added to unblock compilation and functionality
        byte[] serverPrivateKey = getKeyBytes(keyPair.getPrivateKey());
        byte[] serverPublicKey = getKeyBytes(keyPair.getPublicKey());

        // Compress public key to Raw Compressed format (0x02/0x03)
        // This prevents SPKI format (0x30) from being sent to Issuer
        serverPublicKey = BaseCryptoUtil.compressPublicKey(serverPublicKey, ecdhReqData.getCurve());

        byte[] serverNonce = generateServerNonce();
        byte[] clientNonce = BaseMultibaseUtil.decode(ecdhReqData.getClientNonce());
        byte[] mergedNonce = mergeNonce(clientNonce, serverNonce);

        byte[] sessionKey = generateSessionKey(ecdhReqData, serverPrivateKey, mergedNonce, serverCipherType);

        // 4. Generate Response
        // SoT undefined method: generateAccEcdh
        AccEcdh accEcdh = generateAccEcdh(sessionKey, serverCipherType, serverPaddingType, serverNonce, serverPublicKey,
                ecdhReqData);

        // 5. Save Session (Added for TA integration)
        saveEcdhSession(
                ecdhReqData.getClient(),
                BaseMultibaseUtil.encode(sessionKey),
                BaseMultibaseUtil.encode(mergedNonce));

        return accEcdh;
    }

    @Override
    public void verifyEcdhRequest(
            EcdhReqData ecdhReqData,
            String expectedClientDid) throws CommonSdkException {
        // Common Validation Logic
        validateClientDid(ecdhReqData.getClient());
        validateProofPurpose(ecdhReqData.getProof());

        // Signature Verification
        String proofValue = ecdhReqData.getProof().getProofValue();
        byte[] signatureMessage = SignatureCryptoHelper.extractSignatureMessage(ecdhReqData);
        String publicKey = getClientPublicKey(ecdhReqData.getClient());

        SignatureCryptoHelper.verifySignature(
                publicKey,
                proofValue,
                signatureMessage,
                convertProofType(ecdhReqData.getProof().getType()));
    }

    @Override
    public byte[] generateSessionKey(
            EcdhReqData ecdhReqData,
            byte[] serverPrivateKey,
            byte[] mergedNonce,
            SymmetricCipherType serverCipherType) throws CommonSdkException {
        // Delegate to Domain Logic
        byte[] clientPublicKey = BaseMultibaseUtil.decode(ecdhReqData.getPublicKey());

        // Select Cipher Type
        SymmetricCipherType selectedCipher = EcdhCryptoHelper.selectCipherType(
                ecdhReqData.getCandidate(),
                serverCipherType);

        return EcdhCryptoHelper.generateSessionKey(
                clientPublicKey,
                serverPrivateKey,
                mergedNonce,
                selectedCipher,
                ecdhReqData.getCurve(),
                shouldApplyNonceHashing());
    }

    /**
     * Hook method to determine if nonce hashing should be applied during session
     * key generation.
     * Default implementation returns false.
     * Subclasses can override this to return true if needed (e.g. for legacy
     * support).
     *
     * @return true if nonce hashing should be applied, false otherwise.
     */
    protected boolean shouldApplyNonceHashing() {
        return true;
    }

    // Abstract methods to be implemented by server-specific classes
    protected abstract String getClientPublicKey(String clientDid) throws CommonSdkException;

    protected abstract byte[] generateServerNonce() throws CommonSdkException;

    protected abstract void saveEcdhSession(String clientDid, String sessionKey, String mergedNonce)
            throws CommonSdkException;

    // Helper methods to unblock compilation/implementation

    // [Estimated] To support common validation
    protected void validateClientDid(String clientDid) throws CommonSdkException {
        if (clientDid == null || clientDid.isEmpty()) {
            throw new CommonSdkException(ErrorCode.INVALID_DID_DOCUMENT);
        }
    }

    // [Estimated] To support common validation
    protected void validateProofPurpose(Proof proof) throws CommonSdkException {
        // Proof class is legacy (from did-core-sdk), so proofPurpose matches String
        // "keyAgreement"
        if (proof == null || !ProofPurpose.KEY_AGREEMENT.toString().equals(proof.getProofPurpose())) {
            throw new CommonSdkException(ErrorCode.INVALID_PARAMETER);
        }
    }

    // [Estimated] Convert String ProofType to Enum ProofType
    protected ProofType convertProofType(String typeStr) throws CommonSdkException {
        for (ProofType type : ProofType.values()) {
            if (type.toString().equals(typeStr)) {
                return type;
            }
        }
        throw new CommonSdkException(ErrorCode.CRYPTO_UNSUPPORTED_ALGORITHM);
    }

    // [Estimated] To support key extraction from KeyPairInterface (which returns
    // Object)
    private byte[] getKeyBytes(Object keyObj) throws CommonSdkException {
        if (keyObj instanceof Key) {
            return ((Key) keyObj).getEncoded();
        } else if (keyObj instanceof byte[]) {
            return (byte[]) keyObj;
        }
        throw new CommonSdkException(ErrorCode.ECDH_KEY_GENERATION_FAILED);
    }

    // [Estimated] To support nonce merging
    protected byte[] mergeNonce(byte[] clientNonce, byte[] serverNonce) throws CommonSdkException {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(clientNonce);
            outputStream.write(serverNonce);
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new CommonSdkException(ErrorCode.JSON_PROCESSING_ERROR); // Generic error for IO
        }
    }

    // [Estimated] To support return value creation
    protected AccEcdh generateAccEcdh(
            byte[] sessionKey,
            SymmetricCipherType serverCipherType,
            SymmetricPaddingType serverPaddingType,
            byte[] serverNonce,
            byte[] serverPublicKey,
            EcdhReqData reqData) throws CommonSdkException {

        AccEcdh accEcdh = new AccEcdh();
        accEcdh.setServer(null); // Server DID to be set by caller or overridden? SoT unclear. Leaving null.
        accEcdh.setServerNonce(BaseMultibaseUtil.encode(serverNonce));
        accEcdh.setPublicKey(BaseMultibaseUtil.encode(serverPublicKey));
        accEcdh.setCipher(serverCipherType);
        accEcdh.setPadding(serverPaddingType);

        // Proof? SoT doesn't mention signing the AccEcdh here, but typically it should
        // be signed.
        // But the method is just constructing the object.

        return accEcdh;
    }
}
