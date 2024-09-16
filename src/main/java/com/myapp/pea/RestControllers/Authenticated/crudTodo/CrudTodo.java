package com.myapp.pea.RestControllers.Authenticated.crudTodo;

import com.myapp.pea.Exceptions.NotValidDateException;
import com.myapp.pea.Exceptions.TodoItemNotFoundException;
import com.myapp.pea.RequestResponseModels.TodoModels.TodoRequest;
import com.myapp.pea.RequestResponseModels.TodoModels.TodoResponse;
import com.myapp.pea.Services.TodoService.TodoOperationService;
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
@RequestMapping("/api/authenticated")
public class CrudTodo {

    private final Logger logger = LoggerFactory.getLogger(CrudTodo.class);
    private final TodoOperationService todoOperationService;

    @GetMapping("/find/todo/{id}")
    public ResponseEntity<?> viewDetails(@PathVariable Long id){

        try{
            var todo = todoOperationService.findTodoById(id);

            var listId = todo.getLists() == null ? 0L : todo.getLists().getId();
            var listName = todo.getLists() == null ? "None" : todo.getLists().getListName();
            var todoResponse = TodoResponse.builder()
                    .id(todo.getId())
                    .date(todo.getDate())
                    .title(todo.getTitle())
                    .done(todo.isDone())
                    .shortDescription(todo.getShortDescription())
                    .formattedDate(todo.getFormattedDate())
                    .listId(listId)
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
            todoOperationService.addNewTodo(todoRequest);
            logger.info("List of todo : {}",todoRequest.getListId());
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

    @DeleteMapping("/todo/delete/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable Long id){

        try{
            todoOperationService.deleteTodo(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (TodoItemNotFoundException e){
            Map<String, String> error = new HashMap<>();
            error.put("error",e.getMessage());
            return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/todo/edit")
    public ResponseEntity<?> editTodo(@Valid @RequestBody TodoRequest todoRequest, BindingResult bindingResult){

        Map<String, String> errors = new HashMap<>();

        logger.info("Edit Todo : {}",todoRequest);

        if(bindingResult.hasErrors()){
            bindingResult.getFieldErrors().forEach(error -> {
                errors.put(error.getField(),error.getDefaultMessage());
            });
            return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        }

        try{
            todoOperationService.updateTodo(todoRequest);
            logger.info("List of edit todo : {}",todoRequest.getListId());
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

    @PatchMapping("/todo/{id}/mark-as-done")
    public ResponseEntity<?> markAsDone(@PathVariable Long id){

        logger.info("Mark as done Id : {}",id);

        try{
            todoOperationService.markAsComplete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){

            Map<String, String> error = new HashMap<>();
            error.put("error",e.getMessage());
            return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
        }

    }

}
