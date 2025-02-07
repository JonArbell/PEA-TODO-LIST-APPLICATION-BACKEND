package com.myapp.pea.ExceptionErrorsHandler;

import com.myapp.pea.ExceptionErrorsHandler.CustomExceptionErrors.ListNotFoundException;
import com.myapp.pea.ExceptionErrorsHandler.CustomExceptionErrors.TodoNotFoundException;
import com.myapp.pea.ExceptionErrorsHandler.CustomExceptionErrors.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var errors = new HashMap<String, String>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            log.info("Method Argument Not Valid Exception : {} - {}",error.getObjectName(),error.getDefaultMessage());
            errors.put("error", error.getDefaultMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {

        return new ResponseEntity<>(Map.of("RuntimeException",ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFoundException ex) {

        return new ResponseEntity<>(Map.of(ex.toString(),ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleTodoNotFoundException(TodoNotFoundException ex) {

        return new ResponseEntity<>(Map.of(ex.toString(),ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ListNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleListNotFoundException(ListNotFoundException ex) {

        return new ResponseEntity<>(Map.of(ex.toString(),ex.getMessage()), HttpStatus.NOT_FOUND);
    }



}
