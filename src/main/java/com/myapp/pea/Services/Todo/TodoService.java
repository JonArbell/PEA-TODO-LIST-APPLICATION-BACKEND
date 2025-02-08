package com.myapp.pea.Services.Todo;

import com.myapp.pea.DTO.Request.TODO.TodoAddRequestDTO;
import com.myapp.pea.DTO.Request.TODO.TodoUpdateRequestDTO;
import com.myapp.pea.DTO.Response.TodoResponseDTO;
import com.myapp.pea.Entities.Todo;
import com.myapp.pea.ExceptionErrorsHandler.CustomExceptionErrors.ListNotFoundException;
import com.myapp.pea.ExceptionErrorsHandler.CustomExceptionErrors.TodoNotFoundException;
import com.myapp.pea.Repositories.ListRepo;
import com.myapp.pea.Repositories.TodoRepo;
import com.myapp.pea.Services.User.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
    private final UserService userService;

    public Long getCurrentUserId() {
        return userService.getCurrentUser().getId();
    }

    @CacheEvict(value = "todos",key = "'allTodos-'+ #root.target.getCurrentUserId()")
    public TodoResponseDTO addTodo(TodoAddRequestDTO todoRequest){

        var todo = Todo.builder()
                .title(todoRequest.getTitle())
                .done(todoRequest.getDone())
                .user(userService.getCurrentUser())
                .shortDescription(todoRequest.getShortDescription())
                .dueDate(todoRequest.getDueDate())
                .build();

        var saveTodo = todoRepo.save(todo);

        return TodoResponseDTO.fromEntity(saveTodo);

    }

    @Transactional
    @CacheEvict(value = "todos",key = "'allTodos-' + #root.target.getCurrentUserId()")
    @CachePut(value = "todos", key = "#update.id + '-' + #root.target.getCurrentUserId()")
    public TodoResponseDTO updateTodoItem(TodoUpdateRequestDTO update){

        var userId = getCurrentUserId();

        var list = listRepo
                .findByUser_IdAndId(userId, update.getListId())
                .orElseThrow(() -> new ListNotFoundException("List item not found."));

        var currentTodo = todoRepo
                .findByUser_IdAndId(userId, update.getId())
                .orElseThrow(() -> new TodoNotFoundException("Todo item not found."));

        currentTodo.setList(list);
        currentTodo.setDone(update.getDone());
        currentTodo.setTitle(update.getTitle());
        currentTodo.setShortDescription(update.getShortDescription());

        var saveTodo = todoRepo.save(currentTodo);

        return TodoResponseDTO.fromEntity(saveTodo);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Caching(evict = {
            @CacheEvict(value = "todos",key = "'allTodos-' + #root.target.getCurrentUserId()"),
            @CacheEvict(value = "todos",key = "#id + '-' + #root.target.getCurrentUserId()")
    })
    public TodoResponseDTO deleteTodoItem(Long id){

        var checkTodo = todoRepo.findByUser_IdAndId(getCurrentUserId(), id)
                .orElseThrow(() -> new TodoNotFoundException("Todo item not found."));

        todoRepo.delete(checkTodo);

        return TodoResponseDTO.fromEntity(checkTodo);

    }

    @Transactional
    @CacheEvict(value = "todos", allEntries = true)
    public int deleteAllTodos(boolean isDelete){

        if(!isDelete)
            return 0;

        return todoRepo.deleteAllByUser_Id(getCurrentUserId());

    }

    @Transactional(readOnly = true)
    @Cacheable(value = "todos",key = "#id + '-'+#root.target.getCurrentUserId()")
    public TodoResponseDTO getTodoItem(Long id){

        var checkTodo = todoRepo.findByUser_IdAndId(getCurrentUserId(), id)
                .orElseThrow(() -> new TodoNotFoundException("Todo item not found."));

        return TodoResponseDTO.fromEntity(checkTodo);

    }

    @Transactional(readOnly = true)
    @Cacheable(value = "todos",key = "'allTodos-'+ #root.target.getCurrentUserId()")
    public List<TodoResponseDTO> getAllTodos(){

        return todoRepo.findAllByUser_Id(getCurrentUserId())
                .stream()
                .map(TodoResponseDTO::fromEntity)
                .toList();

    }

}
