package org.bram.exceptions;

public class IncorrectOldEmailException extends DetailsAlreadyInUseException{
    public IncorrectOldEmailException(String message) {
        super(message);
    }
}
