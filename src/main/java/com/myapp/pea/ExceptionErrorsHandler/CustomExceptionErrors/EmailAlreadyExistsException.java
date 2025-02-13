package com.myapp.pea.ExceptionErrorsHandler.CustomExceptionErrors;

public class EmailAlreadyExistsException extends RuntimeException{

    public EmailAlreadyExistsException(String message){
        super(message);
    }

    @Override
    public String toString() {
        return "EmailAlreadyExistsException";
    }
}
