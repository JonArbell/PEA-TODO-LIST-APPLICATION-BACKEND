package com.myapp.pea.ExceptionErrorsHandler;

import com.myapp.pea.ExceptionErrorsHandler.CustomExceptionErrors.ListNotFoundException;
import com.myapp.pea.ExceptionErrorsHandler.CustomExceptionErrors.TodoNotFoundException;
import com.myapp.pea.ExceptionErrorsHandler.CustomExceptionErrors.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
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

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUsernameNotFoundException(UsernameNotFoundException ex) {

        return new ResponseEntity<>(Map.of("EmailNotFoundException",ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleBadCredentialsException(BadCredentialsException ex) {

        return new ResponseEntity<>(Map.of("BadCredentialsException",ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFoundException ex) {

        return new ResponseEntity<>(Map.of("UserNotFoundException",ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleTodoNotFoundException(TodoNotFoundException ex) {

        return new ResponseEntity<>(Map.of(ex.toString(),ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ListNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleListNotFoundException(ListNotFoundException ex) {

        return new ResponseEntity<>(Map.of(ex.toString(),ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(NoHandlerFoundException ex) {

        Map<String, String> response = new HashMap<>();
        response.put("error", "Not Found");
        response.put("message", "The requested resource does not exist.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
