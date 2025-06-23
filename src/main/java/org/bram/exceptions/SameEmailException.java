package org.bram.exceptions;

public class SameEmailException extends DetailsAlreadyInUseException{
    public SameEmailException(String message) {
        super(message);
    }
}
