package org.bram.exceptions;

public class UserNotFoundException extends DetailsAlreadyInUseException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
