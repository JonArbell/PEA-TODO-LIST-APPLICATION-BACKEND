package com.myapp.pea.Services.TodoService;

import com.myapp.pea.Entities.Todo;
import com.myapp.pea.Repository.TodoRepo;
import com.myapp.pea.Services.AccountService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetTodos {

    private final TodoRepo todoRepo;
    private final UserService userService;

    public List<Todo> getAllTodo(){

        return todoRepo
                .findByUserId(userService.getId());
    }

}
