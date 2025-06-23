package org.bram.exceptions;

public class UserNotLoggedInException extends DetailsAlreadyInUseException{
    public UserNotLoggedInException(String message) {
        super(message);
    }
}
