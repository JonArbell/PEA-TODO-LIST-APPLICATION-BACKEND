package com.myapp.pea.Controllers;

import com.myapp.pea.DTO.Request.TodoRequestDTO;
import com.myapp.pea.DTO.Response.TodoResponseDTO;
import com.myapp.pea.Services.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/add-todo")
    public ResponseEntity<Map<String, TodoResponseDTO>> testAddTodo(@Valid @RequestBody TodoRequestDTO todo){

        var newTodo = todoService.addTodo(todo);

        log.info("New TODO : {}",newTodo);

        return new ResponseEntity<>(Map.of("response",newTodo), HttpStatus.OK);
    }

}
