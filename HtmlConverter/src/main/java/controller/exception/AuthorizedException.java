package controller.exception;

public class AuthorizedException extends RuntimeException {

    public AuthorizedException() {
    }

    public AuthorizedException(String message) {
        super(message);
    }

    public AuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorizedException(Throwable cause) {
        super(cause);
    }

    public AuthorizedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
