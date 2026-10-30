package org.omnione.did.common.exception;

/**
 * Custom exception class for OpenDID-related errors.
 * This exception encapsulates an ErrorCode to provide more detailed error
 * information.
 *
 */

public class CommonSdkException extends RuntimeException {
    private ErrorCode errorCode;
    private ErrorResponse errorResponse;

    /**
     * Constructs a new CommonSdkException with the specified error code.
     *
     * @param errorCode The ErrorCode enum value representing the specific error.
     */
    public CommonSdkException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    /**
     * Constructs a new CommonSdkException with the specified error response.
     *
     * @param errorResponse The ErrorResponse object representing the specific
     *                      error.
     */
    public CommonSdkException(ErrorResponse errorResponse) {
        super(errorResponse.getDescription());
        this.errorResponse = errorResponse;
    }

    public CommonSdkException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }

    public CommonSdkException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
