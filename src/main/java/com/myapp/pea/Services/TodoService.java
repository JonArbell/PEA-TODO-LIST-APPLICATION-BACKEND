package com.myapp.pea.Services;

import com.myapp.pea.DTO.Request.TodoAddRequestDTO;
import com.myapp.pea.DTO.Response.TodoResponseDTO;
import com.myapp.pea.Entities.Todo;
import com.myapp.pea.Repositories.TodoRepo;
import com.myapp.pea.Repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepo todoRepo;
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

    public List<TodoResponseDTO> getAllTodo(){

        return todoRepo.findAll().stream().map(TodoResponseDTO::fromEntity).filter(todo -> todo.getUserId().equals(1L)).toList();
    }

//    public ListResponseDTO addTodoInList(TodoAddRequestDTO todoRequest, ListAddRequestDTO listRequest){
//
//        var user = userRepo.findByGoogleId(3L)
//                .orElseThrow(() -> new RuntimeException("User not found."));
//
//        var listOfTodos = todoRepo.findAll();
//
//
//        var list = List.builder()
//                .listName(listRequest.getListName())
//                .user(user)
//                .date(LocalDateTime.now())
//                .build();
//
//        var listSave = listRepo.save(list);
//
//        return ListResponseDTO.fromEntity(listSave);
//
//    }

}
