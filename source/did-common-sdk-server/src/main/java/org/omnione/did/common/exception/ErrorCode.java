package org.omnione.did.common.exception;

/**
 * Enumeration of error codes used in the DID Verifier system.
 * Each error code contains a unique identifier, a descriptive message, and an associated HTTP status code.
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
