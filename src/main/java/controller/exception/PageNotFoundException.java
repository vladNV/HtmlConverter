package controller.exception;

/**
 * PageNotFoundException class is the unchecked exception, that
 * was invoked if the handle doesn't know about the web page
 * that was requested by a user.
 */

public class PageNotFoundException extends RuntimeException {
    public final static int CODE = 404;
    private String somePage;

    public PageNotFoundException(String somePage) {
        this.somePage = somePage;
    }

    public PageNotFoundException(String message, String somePage) {
        super(message);
        this.somePage = somePage;
    }

    public PageNotFoundException(String message, Throwable cause, String somePage) {
        super(message, cause);
        this.somePage = somePage;
    }

    public PageNotFoundException(Throwable cause, String somePage) {
        super(cause);
        this.somePage = somePage;
    }

    public PageNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String somePage) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.somePage = somePage;
    }

    public String getSomePage() {
        return somePage;
    }
}
