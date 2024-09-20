package com.myapp.pea.Exceptions;

public class TodoItemsNotFoundException extends RuntimeException{

    public TodoItemsNotFoundException(String message){
        super(message);
    }

}
