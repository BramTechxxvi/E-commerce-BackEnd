package org.bram.exceptions;

public class InvalidProductCategory extends DetailsAlreadyInUseException{
    public InvalidProductCategory(String message) {
        super(message);
    }
}
