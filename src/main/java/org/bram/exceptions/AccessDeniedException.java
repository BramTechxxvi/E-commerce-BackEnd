package org.bram.exceptions;

public class AccessDeniedException extends DetailsAlreadyInUseException{
    public AccessDeniedException(String message) {
        super(message);
    }
}
