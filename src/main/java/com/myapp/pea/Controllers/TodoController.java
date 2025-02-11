package com.myapp.pea.Controllers;

import com.myapp.pea.DTO.Request.TODO.TodoAddRequestDTO;
import com.myapp.pea.DTO.Request.TODO.TodoUpdateRequestDTO;
import com.myapp.pea.DTO.Response.TodoResponseDTO;
import com.myapp.pea.Services.Todo.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/authenticated")
@Slf4j
@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/add-todo")
    public ResponseEntity<Map<String, TodoResponseDTO>> addTodo(@Valid @RequestBody TodoAddRequestDTO todo){

        var newTodo = todoService.addTodo(todo);

        log.info("New TODO : {}",newTodo);

        return new ResponseEntity<>(Map.of("added-todo",newTodo), HttpStatus.CREATED);
    }

    @PutMapping("/update-todo")
    public ResponseEntity<Map<String, TodoResponseDTO>> updateTodo(@Valid @RequestBody TodoUpdateRequestDTO updateTodo){

        var updated = todoService.updateTodoItem(updateTodo);

        log.info("Updated Todo: {}",updated);

        return new ResponseEntity<>(Map.of("updated-todo",updated),HttpStatus.OK);
    }

    @DeleteMapping("/delete-todo/{id}")
    public ResponseEntity<Map<String, String>> deleteTodoItem(@PathVariable Long id){

        var deleted = todoService.deleteTodoItem(id);

        log.info("Deleted Todo: {}",deleted);

        return new ResponseEntity<>(Map.of("deleted-todo",deleted.getTitle() + " successfully deleted"),HttpStatus.OK);
    }

    @DeleteMapping("/delete-todo")
    public ResponseEntity<Map<String, Integer>> deleteAllTodo(@RequestParam  boolean isDeleteAll){

        var deleted = todoService.deleteAllTodos(isDeleteAll);

        log.info("Deleted Todos: {}",deleted);

        return new ResponseEntity<>(Map.of("deleted-todos",deleted),HttpStatus.OK);
    }

    @GetMapping("/get-todo/{id}")
    public ResponseEntity<Map<String, TodoResponseDTO>> getTodoItem(@PathVariable Long id){

        var getTodo = todoService.getTodoItem(id);

        log.info("Get Todo: {}",getTodo);

        return new ResponseEntity<>(Map.of("get-todo-by-id",getTodo),HttpStatus.OK);
    }

    @GetMapping("/get-todo")
    public ResponseEntity<Map<String, List<TodoResponseDTO>>> getAllTodo(){

        var allTodo = todoService.getAllTodos();

        log.info("Get all Todo: {}",allTodo);

        return new ResponseEntity<>(Map.of("get-all-todo",allTodo),HttpStatus.OK);
    }

}
