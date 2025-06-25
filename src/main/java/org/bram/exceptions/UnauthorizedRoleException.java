package org.bram.exceptions;

public class UnauthorizedRoleException extends DetailsAlreadyInUseException{
    public UnauthorizedRoleException(String message) {
        super(message);
    }
}
