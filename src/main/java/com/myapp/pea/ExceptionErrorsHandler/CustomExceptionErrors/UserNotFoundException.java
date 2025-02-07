package com.myapp.pea.ExceptionErrorsHandler.CustomExceptionErrors;


public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message){
        super(message);
    }

    @Override
    public String toString() {
        return "UserNotFoundException";
    }
}
