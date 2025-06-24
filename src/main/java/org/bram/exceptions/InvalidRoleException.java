package org.bram.exceptions;

public class InvalidRoleException extends DetailsAlreadyInUseException{
    public InvalidRoleException(String message) {
        super(message);
    }
}
