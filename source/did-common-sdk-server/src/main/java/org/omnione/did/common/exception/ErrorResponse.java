package org.omnione.did.common.exception;


/**
 * Error Response
 */
public class ErrorResponse {
    private final String code;
    private final String description;

    /**
     * Constructor
     *
     * @param code        Error Code
     * @param description Error Description
     */
    public ErrorResponse(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * Constructor
     *
     * @param errorCode Error Code
     */
    public ErrorResponse(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.description = errorCode.getMessage();
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("ErrorResponse{code='%s', description='%s'}", code, description);
    }
}
