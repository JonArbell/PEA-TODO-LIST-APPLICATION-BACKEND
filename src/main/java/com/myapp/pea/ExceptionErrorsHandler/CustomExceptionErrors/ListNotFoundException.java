package com.myapp.pea.ExceptionErrorsHandler.CustomExceptionErrors;

public class ListNotFoundException extends RuntimeException{

    public ListNotFoundException(String message){
        super(message);
    }

    @Override
    public String toString() {
        return "ListNotFoundException";
    }

}
