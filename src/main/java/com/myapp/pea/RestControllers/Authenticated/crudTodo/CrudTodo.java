package com.myapp.pea.RestControllers.Authenticated.crudTodo;

import com.myapp.pea.Exceptions.NotValidDateException;
import com.myapp.pea.Exceptions.TodoItemsNotFoundException;
import com.myapp.pea.RequestResponseModels.MessageResponse;
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
        }catch (TodoItemsNotFoundException e){
            Map<String, String> error = new HashMap<>();
            error.put("findTodoError",e.getMessage());
            return new ResponseEntity<>(error,HttpStatus.FORBIDDEN);
        }

    }

    @PostMapping("/todo/add")
    public ResponseEntity<?> addTodo(@Valid @RequestBody TodoRequest todoRequest, BindingResult bindingResult){

        Map<String, String> errors = new HashMap<>();

        logger.info("Added Todo : {}",todoRequest);

        if(bindingResult.hasErrors()){
            bindingResult.getFieldErrors().forEach(error -> {
                errors.put("addTodoError",error.getDefaultMessage());
            });
            return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        }

        try{
            todoOperationService.addNewTodo(todoRequest);
            logger.info("List of todo : {}",todoRequest.getListId());
            return new ResponseEntity<>(new MessageResponse("Todo item added successfully!"),HttpStatus.CREATED);
        }catch (NotValidDateException e){
            logger.error("NotValidDateException : {}",e.getMessage());
            errors.put("addTodoError",e.getMessage());
        }catch (RuntimeException e){
            logger.error("RuntimeException : {}",e.getMessage());
            errors.put("addTodoError",e.getMessage());
        }

        return new ResponseEntity<>(errors,HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/todo/delete/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable Long id){
        Map<String, String> error = new HashMap<>();
        try{
            todoOperationService.deleteTodo(id);
            return new ResponseEntity<>(new MessageResponse("Todo item deleted successfully!"),HttpStatus.OK);
        } catch (Exception e){
            error.put("deleteTodoError",e.getMessage());
            return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/todo/edit")
    public ResponseEntity<?> editTodo(@Valid @RequestBody TodoRequest todoRequest, BindingResult bindingResult){

        Map<String, String> errors = new HashMap<>();

        logger.info("Edit Todo : {}",todoRequest);

        if(bindingResult.hasErrors()){
            bindingResult.getFieldErrors().forEach(error -> {
                errors.put("editTodoError",error.getDefaultMessage());
            });
            return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        }

        try{
            todoOperationService.updateTodo(todoRequest);
            logger.info("List of edit todo : {}",todoRequest.getListId());
            return new ResponseEntity<>(new MessageResponse("Todo item updated successfully!"),HttpStatus.OK);
        }catch (NotValidDateException e){
            logger.error("NotValidDateException : {}",e.getMessage());
            errors.put("editTodoError",e.getMessage());
        }catch (Exception e){
            logger.error("Exception : {}",e.getMessage());
            errors.put("editTodoError",e.getMessage());
        }

        return new ResponseEntity<>(errors,HttpStatus.FORBIDDEN);
    }

    @PatchMapping("/todo/{id}/mark-as-done")
    public ResponseEntity<?> markAsDone(@PathVariable Long id){

        logger.info("Mark as done Id : {}",id);

        try{
            todoOperationService.markAsComplete(id);
            return new ResponseEntity<>(new MessageResponse("Todo item marked as done!"),HttpStatus.OK);
        }catch (Exception e){
            Map<String, String> error = new HashMap<>();
            error.put("todoDoneError",e.getMessage());
            return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/search-todo")
    public ResponseEntity<?> searchTodoQuery(@RequestParam("search") String search){
        Map<String,String> errors = new HashMap<>();

        if(search.isEmpty() || search.isBlank()){
            errors.put("searchTodoQueryError","Search is empty");
            return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        }

        try{
            var allTodo = todoOperationService.findByTitle(search);

            logger.info("All todo : {}",allTodo);

            return new ResponseEntity<>(allTodo,HttpStatus.OK);
        }catch(Exception e){
            logger.info("Exception search todo : {}",e.getMessage());
            errors.put("searchTodoQueryError",e.getMessage());
            return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
        }

    }

}
