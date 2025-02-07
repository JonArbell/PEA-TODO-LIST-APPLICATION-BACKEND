package com.myapp.pea.ExceptionErrorsHandler.CustomExceptionErrors;

public class TodoNotFoundException extends RuntimeException{

    public TodoNotFoundException(String message){
        super(message);
    }

    @Override
    public String toString() {
        return "TodoNotFoundException";
    }
}
