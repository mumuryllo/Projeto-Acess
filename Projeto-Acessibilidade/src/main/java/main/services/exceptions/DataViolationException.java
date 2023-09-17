package main.services.exceptions;


public class DataViolationException extends RuntimeException {
    private static final Long serialUID=1L;

    public DataViolationException(String message) {
        super(message);
    }
}
