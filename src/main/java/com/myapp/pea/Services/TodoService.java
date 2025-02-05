package com.myapp.pea.Services;

import com.myapp.pea.Model.Todo;
import com.myapp.pea.Model.User;
import com.myapp.pea.Repositories.TodoRepo;
import com.myapp.pea.Repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepo todoRepo;
    private final UserRepo userRepo;

    public void addTodo(Todo todo){

        var user = userRepo.findByGoogleId(1L)
                .orElseThrow(() -> new RuntimeException("User not found."));

        todo.setUser(user);

        todoRepo.save(todo);
    }


}
