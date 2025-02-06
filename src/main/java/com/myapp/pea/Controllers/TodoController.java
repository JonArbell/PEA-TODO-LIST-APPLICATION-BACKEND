package com.myapp.pea.Controllers;

import com.myapp.pea.DTO.Request.TodoAddRequestDTO;
import com.myapp.pea.DTO.Response.TodoResponseDTO;
import com.myapp.pea.Services.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/add-todo")
    public ResponseEntity<Map<String, TodoResponseDTO>> addTodo(@Valid @RequestBody TodoAddRequestDTO todo){

        log.info("Add TODO : {}",todo);

        var newTodo = todoService.addTodo(todo);

        log.info("New TODO : {}",newTodo);

        return new ResponseEntity<>(Map.of("response",newTodo), HttpStatus.OK);
    }

    @GetMapping("/get-all-todo")
    public ResponseEntity<Map<String, List<TodoResponseDTO>>> getAllTodo(){

        return new ResponseEntity<>(Map.of("response",todoService.getAllTodo()), HttpStatus.OK);
    }

}
