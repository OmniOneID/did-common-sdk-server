package org.omnione.did.common.service.impl.signature;

import org.omnione.did.common.datamodel.data.signature.Proof;
import org.omnione.did.common.datamodel.enums.did.ProofPurpose;
import org.omnione.did.common.datamodel.enums.did.ProofType;
import org.omnione.did.common.exception.CommonSdkException;
import org.omnione.did.common.domain.signature.SignatureCryptoHelper;
import org.omnione.did.common.service.signature.SignatureService;
import org.omnione.did.common.util.did.BaseCoreDidUtil;
import org.omnione.did.common.util.multibase.BaseMultibaseUtil;
import org.omnione.did.data.model.did.DidDocument;

public abstract class BaseSignatureServiceImpl implements SignatureService {

    @Override
    public Proof generateProof(
            Object data,
            ProofPurpose proofPurpose,
            ProofType proofType,
            String verificationMethod) throws CommonSdkException {
        // 1. 서명 메시지 생성
        byte[] signatureMessage = SignatureCryptoHelper.generateSignatureMessage(data);

        // 2. 서명 생성
        byte[] signature = signData(signatureMessage, proofPurpose);
        String encodedSignature = BaseMultibaseUtil.encode(signature);

        // 3. Proof 생성
        return Proof.builder()
                .type(proofType)
                .created(getCurrentUTCTime())
                .verificationMethod(verificationMethod)
                .proofPurpose(proofPurpose)
                .proofValue(encodedSignature)
                .build();
    }

    @Override
    public void verifyProof(
            Object data,
            Proof proof,
            DidDocument didDocument) throws CommonSdkException {
        // 1. 서명 메시지 추출
        String proofValue = proof.getProofValue();
        byte[] signatureMessage = SignatureCryptoHelper.extractSignatureMessage(data);

        // 2. 공개키 조회
        String publicKey = BaseCoreDidUtil.getPublicKey(
                didDocument,
                proof.getVerificationMethod());

        // 3. 서명 검증
        SignatureCryptoHelper.verifySignature(
                publicKey,
                proofValue,
                signatureMessage,
                proof.getType());
    }

    @Override
    public void verifyDidDocumentProof(DidDocument didDocument) throws CommonSdkException {
        BaseCoreDidUtil.verifyDidDocKeyProofs(didDocument);
    }

    // 서버별 구현 필요
    protected abstract byte[] signData(byte[] data, ProofPurpose proofPurpose) throws CommonSdkException;

    protected abstract String getCurrentUTCTime();
}
