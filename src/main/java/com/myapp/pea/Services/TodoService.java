package com.myapp.pea.Services;

import com.myapp.pea.DTO.Request.TodoRequestDTO;
import com.myapp.pea.DTO.Response.TodoResponseDTO;
import com.myapp.pea.Entities.Todo;
import com.myapp.pea.Repositories.TodoRepo;
import com.myapp.pea.Repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepo todoRepo;
    private final UserRepo userRepo;

    public TodoResponseDTO addTodo(TodoRequestDTO todoRequest){

        var user = userRepo.findByGoogleId(1L)
                .orElseThrow(() -> new RuntimeException("User not found."));

        var todo = Todo.builder()
                .title(todoRequest.getTitle())
                .done(todoRequest.getDone())
                .user(user)
                .shortDescription(todoRequest.getShortDescription())
                .dueDate(LocalDateTime.now().plusDays(1L))
                .build();

        var saveTodo = todoRepo.save(todo);

        return TodoResponseDTO.fromEntity(saveTodo);

    }

}
