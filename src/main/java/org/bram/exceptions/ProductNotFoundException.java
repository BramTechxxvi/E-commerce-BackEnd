package org.bram.exceptions;

public class ProductNotFoundException extends DetailsAlreadyInUseException{
    public ProductNotFoundException(String message) {
        super(message);
    }
}
