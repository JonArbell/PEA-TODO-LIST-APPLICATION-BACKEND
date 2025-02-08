package com.myapp.pea.Services;

import com.myapp.pea.DTO.Request.TODO.TodoAddRequestDTO;
import com.myapp.pea.DTO.Request.TODO.TodoUpdateRequestDTO;
import com.myapp.pea.DTO.Response.TodoResponseDTO;
import com.myapp.pea.Entities.Todo;
import com.myapp.pea.Entities.User;
import com.myapp.pea.ExceptionErrorsHandler.CustomExceptionErrors.ListNotFoundException;
import com.myapp.pea.ExceptionErrorsHandler.CustomExceptionErrors.TodoNotFoundException;
import com.myapp.pea.ExceptionErrorsHandler.CustomExceptionErrors.UserNotFoundException;
import com.myapp.pea.Repositories.ListRepo;
import com.myapp.pea.Repositories.TodoRepo;
import com.myapp.pea.Repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepo todoRepo;
    private final ListRepo listRepo;
    private final UserRepo userRepo;

    private User getCurrentUser() {
        return userRepo.findByGoogleId(3L)
                .orElseThrow(() -> new UserNotFoundException("User not found."));
    }

    public TodoResponseDTO addTodo(TodoAddRequestDTO todoRequest){

        var todo = Todo.builder()
                .title(todoRequest.getTitle())
                .done(todoRequest.getDone())
                .user(getCurrentUser())
                .shortDescription(todoRequest.getShortDescription())
                .dueDate(todoRequest.getDueDate())
                .build();

        var saveTodo = todoRepo.save(todo);

        return TodoResponseDTO.fromEntity(saveTodo);

    }

    @Transactional
    public TodoResponseDTO updateTodo(TodoUpdateRequestDTO update){

        var user = getCurrentUser();

        var list = listRepo
                .findByUser_IdAndId(user.getId(), update.getListId())
                .orElseThrow(() -> new ListNotFoundException("List item not found."));

        var currentTodo = todoRepo
                .findByUser_IdAndId(user.getId(), update.getId())
                .orElseThrow(() -> new TodoNotFoundException("Todo item not found."));

        currentTodo.setList(list);
        currentTodo.setDone(update.getDone());
        currentTodo.setTitle(update.getTitle());
        currentTodo.setShortDescription(update.getShortDescription());

        var saveTodo = todoRepo.save(currentTodo);

        return TodoResponseDTO.fromEntity(saveTodo);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TodoResponseDTO deleteTodoById(Long id){

        var checkTodo = todoRepo.findByUser_IdAndId(getCurrentUser().getId(), id)
                .orElseThrow(() -> new TodoNotFoundException("Todo item not found."));

        todoRepo.delete(checkTodo);

        return TodoResponseDTO.fromEntity(checkTodo);

    }

    @Transactional
    public int deleteAllTodos(boolean isDelete){

        if(!isDelete)
            return 0;

        return todoRepo.deleteAllByUser_Id(getCurrentUser().getId());

    }

    @Cacheable(value = "todos",key = "#id")
    @Transactional(readOnly = true)
    public TodoResponseDTO getTodoById(Long id){

        var checkTodo = todoRepo.findByUser_IdAndId(getCurrentUser().getId(), id)
                .orElseThrow(() -> new TodoNotFoundException("Todo item not found."));

        return TodoResponseDTO.fromEntity(checkTodo);

    }

    @Transactional(readOnly = true)
    public List<TodoResponseDTO> getAllTodo(){

        return todoRepo.findAllByUser_Id(getCurrentUser().getId())
                .stream()
                .map(TodoResponseDTO::fromEntity)
                .toList();

    }

}
