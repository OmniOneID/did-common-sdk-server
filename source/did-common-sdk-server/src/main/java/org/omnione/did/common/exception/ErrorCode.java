package org.omnione.did.common.exception;

/**
 * Enumeration of error codes used in the DID Verifier system.
 * Each error code contains a unique identifier, a descriptive message, and an
 * associated HTTP status code.
 *
 */

public enum ErrorCode {

    // Data Processing Errors (100-199)
    JSON_SERIALIZE_FAILED("00100", "Failed to Json serialize.", 500),
    JSON_SERIALIZE_SORT_FAILED("00101", "Failed to Json serialize and sort.", 500),
    JSON_DESERIALIZE_FAILED("00102", "Failed to Json deserialize.", 500),
    INVALID_DATE_TIME("00103", "Invalid date time format.", 400),

    // QR Code Errors (200-299)
    QR_CODE_GENERATION_FAILED("00200", "Failed to generate QR code image.", 500),
    QR_CODE_IO_ERROR("00201", "Failed to write QR code image to output stream.", 500),
    QR_CODE_ENCODING_ERROR("00202", "Failed to encode QR code content.", 500),

    // HTTP Client Errors (300-399)
    HTTP_CLIENT_ERROR("00300", "Failed to send HTTP request.", 500),

    // Signature (400-409)
    SIGNATURE_VERIFICATION_FAILED("00401", "Failed to verify signature", 500),
    INVALID_SIGNATURE("00402", "Invalid signature", 500),
    PROOF_GENERATION_FAILED("00403", "Failed to generate proof", 500),
    SIGNATURE_MESSAGE_GENERATION_FAILED("00404", "Failed to generate signature message", 500),

    // ECDH (410-419)
    ECDH_KEY_GENERATION_FAILED("00410", "Failed to generate ECDH key", 500),
    INVALID_CLIENT_NONCE("00411", "Invalid client nonce", 500),
    SESSION_KEY_GENERATION_FAILED("00412", "Failed to generate session key", 500),

    // Crypto (420-429)
    CRYPTO_UNSUPPORTED_ALGORITHM("00420", "Unsupported cryptographic algorithm", 500),
    INVALID_ECC_CURVE_TYPE("00421", "Invalid ECC curve type", 400),
    INVALID_SYMMETRIC_CIPHER_TYPE("00422", "Invalid symmetric cipher type", 400),
    INVALID_SYMMETRIC_PADDING_TYPE("00423", "Invalid symmetric padding type", 400),
    NONCE_MERGE_FAILED("00424", "Failed to merge nonce", 500),

    // DID Document (500-599)
    INVALID_DID_DOCUMENT("00501", "Invalid DID document", 400),
    DID_DOCUMENT_VERIFICATION_FAILED("00502", "Failed to verify DID document", 500),
    INVOKED_DOCUMENT_GENERATION_FAILED("00503", "Failed to generate invoked DID document", 500),
    DID_DOC_VERIFICATION_METHOD_NOT_FOUND("00504", "Verification method not found in DID Document", 500),
    DIDDOC_GENERATION_FAILED("00505", "Failed to generate DID Document", 500),
    DIDDOC_REQUIRED_KEY_MISSING("00506", "Required key missing in DID document", 500),

    // Wallet (600-699)
    WALLET_CREATION_FAILED("00601", "Failed to create wallet", 500),
    WALLET_LOAD_FAILED("00602", "Failed to load wallet", 500),
    KEY_GENERATION_FAILED("00603", "Failed to generate key", 500),
    SIGNATURE_GENERATION_FAILED("00604", "Failed to generate signature", 500),
    FAILED_TO_LOAD_KEY_ELEMENT("00605", "Failed to load key element", 500),
    WALLET_ALREADY_EXISTS("00606", "Wallet already exists", 400),
    KEY_ALREADY_EXISTS("00607", "Key already exists", 400),
    FAILED_TO_GET_FILE_WALLET_MANAGER("00608", "Failed to get file wallet manager", 500),

    // Token (700-799)
    INVALID_ACCESS_TOKEN("00701", "Invalid access token", 401),
    TOKEN_GENERATION_FAILED("00702", "Failed to generate token", 500),
    TOKEN_SIGNATURE_VERIFICATION_FAILED("00703", "Token signature verification failed", 401),
    TOKEN_PARSE_FAILED("00704", "Failed to parse token", 500),
    EXPIRED_TOKEN("00705", "Token has expired", 401),

    // VC (800-899)
    VC_CREATION_FAILED("00801", "Failed to create Verifiable Credential", 500),
    VC_PARSE_FAILED("00802", "Failed to parse Verifiable Credential", 500),
    VC_SCHEMA_PARSE_FAILED("00803", "Failed to parse VC schema", 500),
    VC_META_PARSE_FAILED("00804", "Failed to parse VC metadata", 500),
    VC_GENERATION_FAILED("00805", "Failed to generate Verifiable Credential", 500),
    VC_SIGNATURE_EXTRACTION_FAILED("00806", "Failed to extract VC signature message", 500),
    VC_PROOF_SETTING_FAILED("00807", "Failed to set VC proof", 500),
    VC_VERIFICATION_FAILED("00808", "Failed to verify Verifiable Credential", 500),
    VC_SIGNING_FAILED("00809", "Failed to sign Verifiable Credential", 500),

    // Blockchain (900-999)
    BLOCKCHAIN_CONNECT_FAILED("00901", "Failed to connect to blockchain", 500),
    BLOCKCHAIN_REGISTER_DID_DOCUMENT_FAILED("00902", "Failed to register DID document", 500),
    BLOCKCHAIN_GET_DID_DOCUMENT_FAILED("00903", "Failed to get DID document", 500),
    BLOCKCHAIN_REGISTER_VC_METADATA_FAILED("00904", "Failed to register VC metadata", 500),
    BLOCKCHAIN_GET_VC_METADATA_FAILED("00905", "Failed to get VC metadata", 500),
    BLOCKCHAIN_SERIALIZATION_FAILED("00906", "Failed to serialize blockchain data", 500),
    BLOCKCHAIN_UPDATE_DID_DOCUMENT_FAILED("00907", "Failed to update DID document", 500),
    BLOCKCHAIN_UPDATE_VC_STATUS_FAILED("00908", "Failed to update VC status", 500),
    BLOCKCHAIN_REMOVE_INDEX_FAILED("00909", "Failed to remove index", 500),

    // General (1000-1099)
    JSON_PROCESSING_ERROR("01001", "JSON processing error", 500),
    ENCODING_FAILED("01002", "Encoding failed", 500),
    INVALID_PARAMETER("01003", "Invalid parameter", 400),
    NOT_IMPLEMENTED("01004", "Not implemented", 501),
    NOT_SUPPORTED_OPERATION("01005", "Operation not supported", 400),
    QR_IMAGE_CONVERT_FAILED("01006", "Failed to convert QR image", 500),
    UNKNOWN_ERROR("01099", "Unknown error", 500),
    ;

    private final String code;
    private final String message;
    private final int httpStatus;

    /**
     * Constructor for ErrorCode enum.
     *
     * @param code       Error Code
     * @param message    Error Message
     * @param httpStatus HTTP Status Code
     */
    ErrorCode(String code, String message, int httpStatus) {
        this.code = "S" + "SDK" + "UTL" + code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    /**
     * Get the error code.
     *
     * @return Error Code
     */
    public static String getMessageByCode(String code) {
        for (ErrorCode errorCode : values()) {
            if (errorCode.getCode().equals(code)) {
                return errorCode.getMessage();
            }
        }
        return "Unknown error code: " + code;
    }

    @Override
    public String toString() {
        return String.format("ErrorCode{code='%s', message='%s', httpStatus=%d}", code, message, httpStatus);
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}
