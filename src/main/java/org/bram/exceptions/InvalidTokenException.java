package org.bram.exceptions;

public class InvalidTokenException extends DetailsAlreadyInUseException{
    public InvalidTokenException(String message) {
        super(message);
    }
}
