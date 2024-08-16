package com.myapp.pea.Exceptions;

public class TodoListNotFoundException extends RuntimeException{

    public TodoListNotFoundException(String message){
        super(message);
    }

}
