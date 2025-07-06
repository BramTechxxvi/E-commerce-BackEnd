package org.bram.exceptions;

public class QuantityUnAvailableException extends DetailsAlreadyInUseException{
    public QuantityUnAvailableException(String message) {
        super(message);
    }
}
