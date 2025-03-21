package it.epicode.exceptions;

public class WrongSelectionException extends RuntimeException {
    public WrongSelectionException(String message) {
        super(message);
    }
}
