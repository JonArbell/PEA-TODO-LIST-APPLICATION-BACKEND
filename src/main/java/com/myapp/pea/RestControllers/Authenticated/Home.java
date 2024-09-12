package com.myapp.pea.RestControllers.Authenticated;

import com.myapp.pea.RequestResponseModels.TodoModels.TodoResponse;
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

    @GetMapping("/home")
    public ResponseEntity<?> home(){

        var requestList = new ArrayList<TodoResponse>();

        getTasks.allTodoDateModified().forEach(data -> {
            var todoRequest = TodoResponse.builder()
                    .title(data.getTitle())
                    .done(data.isDone())
                    .date(data.getDate())
                    .dateModified(data.getDateModified())
                    .shortDescription(data.getShortDescription())
                    .formattedDate(data.getFormattedDate())
                    .build();
            requestList.add(todoRequest);
        });

        return new ResponseEntity<>(requestList,HttpStatus.OK);
    }

}
