package org.bram.exceptions;

public class SamePasswordException extends DetailsAlreadyInUseException{
    public SamePasswordException(String message) {
        super(message);
    }
}
