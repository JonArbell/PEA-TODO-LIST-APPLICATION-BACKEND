package com.myapp.pea.Exceptions;

public class TodoItemNotFoundException extends RuntimeException{

    public TodoItemNotFoundException(String message){
        super(message);
    }

}
