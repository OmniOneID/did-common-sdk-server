package org.omnione.did.common.exception;


/**
 * Custom exception class for OpenDID-related errors.
 * This exception encapsulates an ErrorCode to provide more detailed error information.
 *
 */

public class OpenDidException extends RuntimeException{
    private ErrorCode errorCode;
    private ErrorResponse errorResponse;

    /**
     * Constructs a new OpenDidException with the specified error code.
     *
     * @param errorCode The ErrorCode enum value representing the specific error.
     */
    public OpenDidException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

   /**
     * Constructs a new OpenDidException with the specified error response.
     *
     * @param errorResponse The ErrorResponse object representing the specific error.
     */
    public OpenDidException(ErrorResponse errorResponse) {
        super(errorResponse.getDescription());
        this.errorResponse = errorResponse;
    }
}
