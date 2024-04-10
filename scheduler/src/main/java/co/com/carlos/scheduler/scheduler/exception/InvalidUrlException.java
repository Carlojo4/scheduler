package co.com.carlos.scheduler.scheduler.exception;

public class InvalidUrlException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public InvalidUrlException(final String message) {
        super(message);
    }

}
