package com.myapp.pea.Services;

import com.myapp.pea.DTO.Request.TODO.TodoAddRequestDTO;
import com.myapp.pea.DTO.Request.TODO.TodoUpdateRequestDTO;
import com.myapp.pea.DTO.Response.TodoResponseDTO;
import com.myapp.pea.Entities.Todo;
import com.myapp.pea.Repositories.ListRepo;
import com.myapp.pea.Repositories.TodoRepo;
import com.myapp.pea.Repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepo todoRepo;
    private final ListRepo listRepo;
    private final UserRepo userRepo;

    public TodoResponseDTO addTodo(TodoAddRequestDTO todoRequest){

        var user = userRepo.findByGoogleId(3L)
                .orElseThrow(() -> new RuntimeException("User not found."));

        var todo = Todo.builder()
                .title(todoRequest.getTitle())
                .done(todoRequest.getDone())
                .user(user)
                .shortDescription(todoRequest.getShortDescription())
                .dueDate(todoRequest.getDueDate())
                .build();

        var saveTodo = todoRepo.save(todo);

        return TodoResponseDTO.fromEntity(saveTodo);

    }

    public TodoResponseDTO updateTodo(TodoUpdateRequestDTO update){

        var user = userRepo.findByGoogleId(3L)
                .orElseThrow(() -> new RuntimeException("User not found."));

        var list = listRepo
                .findByUser_IdAndId(user.getId(), update.getListId())
                .orElseThrow(() -> new RuntimeException("List item not found."));

        var currentTodo = todoRepo
                .findByUser_IdAndId(user.getId(), update.getId())
                .orElseThrow(() -> new RuntimeException("Todo item not found."));

        currentTodo.setList(list);
        currentTodo.setDone(update.getDone());
        currentTodo.setTitle(update.getTitle());
        currentTodo.setShortDescription(update.getShortDescription());

        var saveTodo = todoRepo.save(currentTodo);

        return TodoResponseDTO.fromEntity(saveTodo);
    }

    public TodoResponseDTO deleteTodo(Long id){

        var user = userRepo.findByGoogleId(3L)
                .orElseThrow(() -> new RuntimeException("User not found."));

        var checkTodo = todoRepo.findByUser_IdAndId(user.getId(), id)
                .orElseThrow(() -> new RuntimeException("Todo item not found."));

        todoRepo.delete(checkTodo);

        return TodoResponseDTO.fromEntity(checkTodo);

    }

    public TodoResponseDTO getTodoBy(Long id){

        var user = userRepo.findByGoogleId(3L)
                .orElseThrow(() -> new RuntimeException("User not found."));

        var checkTodo = todoRepo.findByUser_IdAndId(user.getId(), id)
                .orElseThrow(() -> new RuntimeException("Todo item not found."));

        return TodoResponseDTO.fromEntity(checkTodo);

    }

}
