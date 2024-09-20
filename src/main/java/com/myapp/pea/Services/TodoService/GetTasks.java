package com.myapp.pea.Services.TodoService;


import com.myapp.pea.Entities.Todo;
import com.myapp.pea.Repository.TodoRepo;
import com.myapp.pea.Services.AccountService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@Controller
public class GetTasks {

    private final UserService userService;
    private final TodoRepo todoRepo;

    public List<Todo> getAllTodo(){

        return todoRepo
                .findByUserId(userService.getId());
    }

}
