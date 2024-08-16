package com.myapp.pea.Services.TodoService;


import com.myapp.pea.Models.Todo;
import com.myapp.pea.Repository.TodoRepo;
import com.myapp.pea.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@Controller
public class GetTasks {

    private final UserService userService;
    private final TodoRepo todoRepo;

    public List<Todo> allTodoDateModified(){

        List<Todo> allTodo = todoRepo
                .findByUserId(userService.getId());

        return allTodo
                .stream()
                .sorted(Comparator.comparing(Todo::getDateModified))
                .toList()
                .reversed();
    }

    public List<Todo> allTodoTitle(){
        List<Todo> allTodo = todoRepo
                .findByUserId(userService.getId());

        return allTodo
                .stream()
                .sorted(Comparator.comparing(Todo::getTitle))
                .toList();
    }

    public List<Todo> allTodoTargetDate(){
        List<Todo> allTodo = todoRepo
                .findByUserId(userService.getId());

        return allTodo
                .stream()
                .sorted(Comparator.comparing(Todo::getDate))
                .toList();
    }
}
