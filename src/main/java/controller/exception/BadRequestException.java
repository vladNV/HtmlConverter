package controller.exception;

/**
 * BadRequestException class is the unchecked exception that was thrown
 * if a request is bad, incorrect command, handler didn't handle it.
 */

public class BadRequestException extends RuntimeException {
    public final static int CODE = 400;
    private String badRequest;

    public BadRequestException(String badRequest) {
        this.badRequest = badRequest;
    }

    public BadRequestException(String message, String badRequest) {
        super(message);
        this.badRequest = badRequest;
    }

    public BadRequestException(String message, Throwable cause, String badRequest) {
        super(message, cause);
        this.badRequest = badRequest;
    }

    public BadRequestException(Throwable cause, String badRequest) {
        super(cause);
        this.badRequest = badRequest;
    }

    public BadRequestException(String message, Throwable cause,
                               boolean enableSuppression, boolean writableStackTrace,
                               String badRequest) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.badRequest = badRequest;
    }

    public String getBadRequest() {
        return badRequest;
    }
}
