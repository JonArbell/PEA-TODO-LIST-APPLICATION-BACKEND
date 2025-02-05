package com.myapp.pea.Controllers;

import com.myapp.pea.Model.Todo;
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
    public ResponseEntity<Map<String,String>> testAddTodo(@Valid @RequestBody Todo todo){

        todoService.addTodo(todo);

        return new ResponseEntity<>(Map.of("200",HttpStatus.OK.getReasonPhrase()), HttpStatus.OK);
    }

}
