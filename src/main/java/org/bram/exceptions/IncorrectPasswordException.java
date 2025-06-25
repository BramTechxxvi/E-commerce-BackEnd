package org.bram.exceptions;

public class IncorrectPasswordException extends DetailsAlreadyInUseException{
    public IncorrectPasswordException(String message) {
        super(message);
    }
}
