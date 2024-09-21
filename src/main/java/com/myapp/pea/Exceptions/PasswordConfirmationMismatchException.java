package com.myapp.pea.Exceptions;

public class PasswordConfirmationMismatchException extends RuntimeException{

    public PasswordConfirmationMismatchException(String message){
        super(message);
    }

}
