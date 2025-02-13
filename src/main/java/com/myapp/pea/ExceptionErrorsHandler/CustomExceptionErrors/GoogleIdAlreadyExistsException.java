package com.myapp.pea.ExceptionErrorsHandler.CustomExceptionErrors;

public class GoogleIdAlreadyExistsException extends RuntimeException{

    public GoogleIdAlreadyExistsException(String message){
        super(message);
    }

    @Override
    public String toString() {
        return "GoogleIdAlreadyExistsException";
    }
}
