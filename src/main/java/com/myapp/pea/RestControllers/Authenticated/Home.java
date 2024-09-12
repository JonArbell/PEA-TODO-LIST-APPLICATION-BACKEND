package com.myapp.pea.RestControllers.Authenticated;

import com.myapp.pea.RequestResponseModels.ListsModels.ListsResponse;
import com.myapp.pea.RequestResponseModels.TodoModels.TodoResponse;
import com.myapp.pea.RequestResponseModels.WebPagesResponse.HomeResponse;
import com.myapp.pea.Services.ListsService.GetLists;
import com.myapp.pea.Services.TodoService.GetTasks;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;

@RestController
@AllArgsConstructor
public class Home {

    private final GetTasks getTasks;
    private final GetLists getLists;

    @GetMapping("/home")
    public ResponseEntity<?> home(){

        var listsOfTodos = new ArrayList<TodoResponse>();
        getTasks.allTodoDateModified().forEach(data -> {
            var listName = data.getLists() == null ? "None" : data.getLists().getListName();

            var todo = TodoResponse.builder()
                    .id(data.getId())
                    .title(data.getTitle())
                    .done(data.isDone())
                    .date(data.getDate())
                    .dateModified(data.getDateModified())
                    .shortDescription(data.getShortDescription())
                    .formattedDate(data.getFormattedDate())
                    .listName(listName)
                    .build();
            listsOfTodos.add(todo);
        });

        var listsOfLists = new ArrayList<ListsResponse>();
        getLists.allListsDateModified().forEach(data -> {
            var lists = ListsResponse.builder()
                    .listName(data.getListName())
                    .id(data.getId())
                    .build();

            listsOfLists.add(lists);
        });

        return new ResponseEntity<>(new HomeResponse(listsOfTodos,listsOfLists),HttpStatus.OK);
    }

}
