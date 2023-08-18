package dev.marksduarte.springbootbase.exceptions;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String message) {}

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
