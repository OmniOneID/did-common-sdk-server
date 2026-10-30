package org.omnione.did.common.util.lss;

/**
 * Exception thrown when LSS (Ledger Service Server) operations fail.
 */
public class LssException extends RuntimeException {

    private final int statusCode;

    public LssException(String message) {
        super(message);
        this.statusCode = -1;
    }

    public LssException(String message, Throwable cause) {
        super(message, cause);
        this.statusCode = -1;
    }

    public LssException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public LssException(int statusCode, String message, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public boolean isClientError() {
        return statusCode >= 400 && statusCode < 500;
    }

    public boolean isServerError() {
        return statusCode >= 500 && statusCode < 600;
    }

    public boolean isAuthenticationError() {
        return statusCode == 401;
    }

    public boolean isCommunicationError() {
        return statusCode == -1 || statusCode == 500;
    }
}
