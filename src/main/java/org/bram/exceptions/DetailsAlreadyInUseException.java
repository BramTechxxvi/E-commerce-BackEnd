package org.bram.exceptions;

public class DetailsAlreadyInUseException extends RuntimeException{

    public DetailsAlreadyInUseException(String message){
        super(message);
    }
}
