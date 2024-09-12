package com.myapp.pea.RestControllers.Authenticated.crudTodo;

import com.myapp.pea.Exceptions.NotValidDateException;
import com.myapp.pea.Exceptions.TodoItemNotFoundException;
import com.myapp.pea.RequestResponseModels.TodoModels.TodoRequest;
import com.myapp.pea.RequestResponseModels.TodoModels.TodoResponse;
import com.myapp.pea.Services.TodoService.TaskOperation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class CrudTodo {

    private final Logger logger = LoggerFactory.getLogger(CrudTodo.class);
    private final TaskOperation taskOperation;

    @GetMapping("/find/todo/{id}")
    public ResponseEntity<?> viewDetails(@PathVariable Long id){

        try{
            var todo = taskOperation.findTodoById(id);

            var listName = todo.getLists() == null ? "None" : todo.getLists().getListName();
            var todoResponse = TodoResponse.builder()
                    .id(todo.getId())
                    .date(todo.getDate())
                    .title(todo.getTitle())
                    .done(todo.isDone())
                    .shortDescription(todo.getShortDescription())
                    .formattedDate(todo.getFormattedDate())
                    .listName(listName)
                    .dateModified(todo.getDateModified())
                    .build();

            return new ResponseEntity<>(todoResponse,HttpStatus.OK);
        }catch (TodoItemNotFoundException e){
            Map<String, String> error = new HashMap<>();
            error.put("message",e.getMessage());
            return new ResponseEntity<>(error,HttpStatus.FORBIDDEN);
        }

    }

    @PostMapping("/todo/add")
    public ResponseEntity<?> addTodo(@Valid @RequestBody TodoRequest todoRequest, BindingResult bindingResult){

        Map<String, String> errors = new HashMap<>();

        logger.info("Added Todo : {}",todoRequest);

        if(bindingResult.hasErrors()){
            bindingResult.getFieldErrors().forEach(error -> {
                errors.put(error.getField(),error.getDefaultMessage());
            });
            return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        }

        try{
            taskOperation.addNewTodo(todoRequest);
            return new ResponseEntity<>(todoRequest,HttpStatus.OK);
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
