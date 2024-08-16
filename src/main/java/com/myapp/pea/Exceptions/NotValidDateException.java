package com.myapp.pea.Exceptions;

public class NotValidDateException extends RuntimeException{

    public NotValidDateException(String message){
        super(message);
    }

}
