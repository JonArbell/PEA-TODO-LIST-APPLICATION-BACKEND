package com.myapp.pea.RestControllers.Authenticated.WebPages.Lists;

import com.myapp.pea.RequestResponseModels.ListsModels.ListsResponse;
import com.myapp.pea.RequestResponseModels.TodoModels.TodoResponse;
import com.myapp.pea.RequestResponseModels.UserModels.UserResponse;
import com.myapp.pea.RequestResponseModels.WebPagesResponse.PagesResponse;
import com.myapp.pea.Services.AccountService.UserService;
import com.myapp.pea.Services.ListsService.GetLists;
import com.myapp.pea.Services.TodoService.GetTasks;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/authenticated")
public class SortByDateModifiedList {

    private final GetTasks getTasks;
    private final GetLists getLists;
    private final UserService userService;

    private List<TodoResponse> getAllTodosResponse(){

        var listsOfTodos = new ArrayList<TodoResponse>();
        getTasks.allTodoDateModified().forEach(data -> {
            var listId = data.getLists() == null ? 0L : data.getLists().getId();

            var todo = TodoResponse.builder()
                    .id(data.getId())
                    .title(data.getTitle())
                    .done(data.isDone())
                    .date(data.getDate())
                    .dateModified(data.getDateModified())
                    .shortDescription(data.getShortDescription())
                    .formattedDate(data.getFormattedDate())
                    .listId(listId)
                    .build();
            listsOfTodos.add(todo);
        });

        return listsOfTodos;
    }

    private UserResponse userResponse(){
        return UserResponse.builder()
                .id(userService.getId())
                .username(userService.getUsername())
                .email(userService.getEmail())
                .firstName(userService.getFirstName())
                .lastName(userService.getLastName())
                .build();
    }

    private List<ListsResponse> getAllListsResponse(){

        var listsOfLists = new ArrayList<ListsResponse>();

        getLists.allListsDateModified().forEach(data -> {
            var lists = ListsResponse.builder()
                    .listName(data.getListName())
                    .id(data.getId())
                    .build();

            listsOfLists.add(lists);
        });

        return listsOfLists;
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> getList(@PathVariable Long id){

        var todosOfThisList = getAllTodosResponse()
                .stream()
                .filter(todo -> todo.getListId() != null && todo.getListId().equals(id))
                .toList();

        return new ResponseEntity<>(new PagesResponse(todosOfThisList,getAllListsResponse(),userResponse()),
                HttpStatus.OK);
    }
}
