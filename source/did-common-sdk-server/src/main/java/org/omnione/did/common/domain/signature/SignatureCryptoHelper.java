package org.omnione.did.common.domain.signature;

import org.omnione.did.common.datamodel.enums.did.ProofType;
import org.omnione.did.common.exception.ErrorCode;
import org.omnione.did.common.exception.CommonSdkException;
import org.omnione.did.common.util.crypto.BaseCryptoUtil;
import org.omnione.did.common.util.JsonUtil;
import org.omnione.did.common.util.digest.BaseDigestUtil;
import java.nio.charset.StandardCharsets;

/**
 * Domain utility for signature operations.
 * Implements Layer 2 Signature logic as per OpenDID Common SDK v1.7.
 */
public class SignatureCryptoHelper {

    /**
     * Verifies a digital signature.
     *
     * @param encodedPublicKey The encoded public key.
     * @param signature        The encoded signature.
     * @param message          The message to verify.
     * @param proofType        The proof type containing the algorithm/curve
     *                         information.
     * @throws CommonSdkException if validation fails or signature verification
     *                            fails.
     */
    public static void verifySignature(String encodedPublicKey, String signature, byte[] message, ProofType proofType)
            throws CommonSdkException {
        // A) Input Validation
        if (encodedPublicKey == null || encodedPublicKey.trim().isEmpty() ||
                signature == null || signature.trim().isEmpty() ||
                message == null || message.length == 0 ||
                proofType == null) {
            throw new CommonSdkException(ErrorCode.INVALID_PARAMETER);
        }

        // B) Verification Logic
        try {
            BaseCryptoUtil.verifySignature(encodedPublicKey, signature, message, proofType.toEccCurveType());
        } catch (CommonSdkException e) {
            // Already an CommonSdkException, check if we need to wrap it to unify error
            // code
            // Guidelines say: unify to SIGNATURE_VERIFICATION_FAILED
            // But if it's already that, just rethrow.
            if (e.getErrorCode() == ErrorCode.SIGNATURE_VERIFICATION_FAILED) {
                throw e;
            }
            // For other CommonSdkExceptions (e.g. format error in BaseCryptoUtil),
            // wrap/rethrow as verification failed
            throw new CommonSdkException(ErrorCode.SIGNATURE_VERIFICATION_FAILED, e);
        } catch (Exception e) {
            // C) Exception Handling - Unify to SIGNATURE_VERIFICATION_FAILED
            throw new CommonSdkException(ErrorCode.SIGNATURE_VERIFICATION_FAILED, e);
        }
    }

    /**
     * Generate signature message.
     * 1) Serialize data to Canonical JSON (sorted keys).
     * 2) Generate SHA-256 hash of the Canonical JSON.
     *
     * @param data Data to sign (will be serialized to Canonical JSON)
     * @return SHA-256 hash of the Canonical JSON bytes
     * @throws CommonSdkException if generation fails
     */
    public static byte[] generateSignatureMessage(Object data) throws CommonSdkException {
        // A) Input Validation
        if (data == null) {
            throw new CommonSdkException(ErrorCode.INVALID_PARAMETER);
        }

        try {
            // B) Canonical Serialization
            String canonicalJson = JsonUtil.serializeAndSort(data);

            // C) Hashing
            return BaseDigestUtil.generateHash(canonicalJson.getBytes(StandardCharsets.UTF_8));
        } catch (CommonSdkException e) {
            throw e;
        } catch (Exception e) {
            // D) Exception Handling
            throw new CommonSdkException(ErrorCode.SIGNATURE_MESSAGE_GENERATION_FAILED, e);
        }
    }

    /**
     * Remove proof value from the data.
     * This method is used to prepare data for signing or verification by removing
     * existing
     * proof values or proof objects as per the data type.
     *
     * @param <T>  Type of the data
     * @param data Data to remove proof value from
     * @return Data with proof value removed
     * @throws CommonSdkException if data is null or proof removal fails
     */
    public static <T> T removeProofValue(T data) throws CommonSdkException {
        // A) Input Validation
        if (data == null) {
            throw new CommonSdkException(ErrorCode.INVALID_PARAMETER);
        }

        T copiedData;
        try {
            // Deep Copy using JsonUtil
            // This ensures that the original object 'data' is not modified.
            String jsonString = JsonUtil.serializeToJson(data);
            // We use the class of the data object for deserialization.
            // Note: For generic types with Type Erasure issues where simple Class-based
            // deserialization
            // might be insufficient (e.g. List<MyObject>), this might fail to fully
            // reconstruct
            // types.
            // However, the current usages (DidDocument, InvokedDidDoc, etc.) are concrete
            // classes
            // or we expect POJOs.
            @SuppressWarnings("unchecked")
            T result = (T) JsonUtil.deserializeFromJson(jsonString, data.getClass());
            copiedData = result;
        } catch (CommonSdkException e) {
            throw e;
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.JSON_SERIALIZE_FAILED, e);
        }

        try {
            Class<?> clazz = copiedData.getClass();
            // 1) Handle DidDocument specific logic (TA: setProof(null), setProofs(null))
            // Check by name to avoid compile-time dependency issues if module structure is
            // complex
            if (clazz.getName().equals("org.omnione.did.data.model.did.DidDocument")) {
                boolean didDocModified = false;
                // setProof(null)
                try {
                    // Try to find setProof. Parameter could be Proof or Object.
                    for (java.lang.reflect.Method method : clazz.getMethods()) {
                        if (method.getName().equals("setProof") && method.getParameterCount() == 1) {
                            method.invoke(copiedData, (Object) null);
                            didDocModified = true;
                        }
                    }
                } catch (Exception ignored) {
                }

                // setProofs(null)
                try {
                    for (java.lang.reflect.Method method : clazz.getMethods()) {
                        if (method.getName().equals("setProofs") && method.getParameterCount() == 1) {
                            method.invoke(copiedData, (Object) null);
                            didDocModified = true;
                        }
                    }
                } catch (Exception ignored) {
                }

                // If it was identified as DidDocument, we return it consistently.
                return copiedData;
            }

            // 2) Reflection: check for getProof() to clear proofValue inside proof
            try {
                java.lang.reflect.Method getProof = clazz.getMethod("getProof");
                Object proof = getProof.invoke(copiedData);
                if (proof != null) {
                    // Look for setProofValue(String) or setProofValue(byte[]) or similar
                    boolean proofValueCleared = false;
                    for (java.lang.reflect.Method method : proof.getClass().getMethods()) {
                        if (method.getName().equals("setProofValue") && method.getParameterCount() == 1) {
                            method.invoke(proof, (Object) null);
                            proofValueCleared = true;
                        }
                    }
                    if (proofValueCleared)
                        return copiedData;
                }
            } catch (NoSuchMethodException ignored) {
                // No getProof
            }

            // 3) Generic: look for setProof(null) / setProofs(null) / setProofValue(null)
            // on the data itself
            boolean modified = false;
            String[] targetMethods = { "setProof", "setProofs", "setProofValue" };
            for (String methodName : targetMethods) {
                for (java.lang.reflect.Method method : clazz.getMethods()) {
                    if (method.getName().equals(methodName) && method.getParameterCount() == 1) {
                        try {
                            method.invoke(copiedData, (Object) null);
                            modified = true;
                        } catch (Exception ignored) {
                        }
                    }
                }
            }

            if (modified) {
                return copiedData;
            }

            // If we reached here and nothing was modified (and it wasn't a DidDocument
            // handled above),
            // and we couldn't find expected methods.
            throw new CommonSdkException(ErrorCode.INVALID_PARAMETER);

        } catch (CommonSdkException e) {
            throw e;
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.INVALID_PARAMETER, e);
        }
    }

    /**
     * Extract signature message from the data.
     * This method orchestrates proof removal and signature message generation.
     * 1) Remove proof value from the data.
     * 2) Generate signature message (Canonical JSON Hash).
     *
     * @param <T>  Type of the data
     * @param data Data to extract signature message from
     * @return SHA-256 hash of the Canonical JSON bytes of the data with proof
     *         removed
     * @throws CommonSdkException if data is null or extraction fails
     */
    public static <T> byte[] extractSignatureMessage(T data) throws CommonSdkException {
        // A) Input Validation
        if (data == null) {
            throw new CommonSdkException(ErrorCode.INVALID_PARAMETER);
        }

        try {
            // B) Remove Proof
            T cleanedData = removeProofValue(data);

            // C) Generate Message
            return generateSignatureMessage(cleanedData);
        } catch (CommonSdkException e) {
            throw e;
        } catch (Exception e) {
            // Should not happen as removeProofValue and generateSignatureMessage handle
            // their exceptions,
            // but for safety wrap any unexpected runtime exception.
            // Using a generic error code or reusing SIGNATURE_MESSAGE_GENERATION_FAILED
            throw new CommonSdkException(ErrorCode.SIGNATURE_MESSAGE_GENERATION_FAILED, e);
        }
    }
}
