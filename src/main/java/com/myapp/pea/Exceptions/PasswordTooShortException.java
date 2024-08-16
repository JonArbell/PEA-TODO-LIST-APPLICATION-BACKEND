package com.myapp.pea.Exceptions;

public class PasswordTooShortException extends RuntimeException{


    public PasswordTooShortException(String message){
        super(message);
    }

}
