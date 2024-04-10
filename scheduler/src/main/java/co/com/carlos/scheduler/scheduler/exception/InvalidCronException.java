package co.com.carlos.scheduler.scheduler.exception;

public class InvalidCronException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public InvalidCronException(final String message) {
        super(message);
    }
}
