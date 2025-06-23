package org.bram.exceptions;

public class IncorrectOldPasswordException extends DetailsAlreadyInUseException{
    public IncorrectOldPasswordException(String message) {
        super(message);
    }
}
