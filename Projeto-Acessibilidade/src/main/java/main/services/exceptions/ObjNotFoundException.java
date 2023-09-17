package main.services.exceptions;


public class ObjNotFoundException extends RuntimeException {
    private static final Long serialUID=1L;

    public ObjNotFoundException(String message) {
        super(message);
    }
}
