package com.myapp.pea.RestControllers.Authenticated.crudTodo;

import com.myapp.pea.Exceptions.NotValidDateException;
import com.myapp.pea.Entities.Todo;
import com.myapp.pea.Services.TodoService.TaskOperation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class CrudTodo {

    private final Logger logger = LoggerFactory.getLogger(CrudTodo.class);
    private final TaskOperation taskOperation;

    @PostMapping("/todo/add")
    public ResponseEntity<?> addTodo(@Valid @RequestBody Todo todo, BindingResult bindingResult){

        Map<String, String> errors = new HashMap<>();

        logger.info("Added Todo : {}",todo);

        if(bindingResult.hasErrors()){
            bindingResult.getFieldErrors().forEach(error -> {
                errors.put(error.getField(),error.getDefaultMessage());
            });
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try{
            taskOperation.addNewTodo(todo);
            return new ResponseEntity<>(todo,HttpStatus.OK);
        }catch (NotValidDateException e){
            logger.error("NotValidDateException : {}",e.getMessage());
            errors.put(NotValidDateException.class.getSimpleName(),e.getMessage());
        }catch (RuntimeException e){
            logger.error("RuntimeException : {}",e.getMessage());
            errors.put(RuntimeException.class.getSimpleName(),e.getMessage());
        }

        return new ResponseEntity<>(errors,HttpStatus.FORBIDDEN);
    }


}
