package com.myapp.pea.RestControllers.Authenticated.WebPagesRequests.Tasks;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/authenticated")
public class SortByTargetDate {

    private final GetTasks getTasks;
    private final GetLists getLists;
    private final UserService userService;

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

    private List<TodoResponse> getAllTodosResponse(){

        var listsOfTodos = new ArrayList<TodoResponse>();
        getTasks.getAllTodo().forEach(data -> {
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

    @GetMapping("/home/sort-by-target-date")
    public ResponseEntity<?> home(){

        var pending = getAllTodosResponse().stream()
                .filter(todo -> !todo.isDone())
                .sorted(Comparator.comparing(TodoResponse::getDate))
                .toList();

        return new ResponseEntity<>(new PagesResponse(pending,getAllListsResponse(),userResponse()), HttpStatus.OK);
    }

    @GetMapping("/todays-tasks/sort-by-target-date")
    public ResponseEntity<?> todayTasks(){

        var todayTask = getAllTodosResponse()
                .stream()
                .filter(date -> date.getDate().isEqual(LocalDate.now()))
                .filter(done -> !done.isDone())
                .sorted(Comparator.comparing(TodoResponse::getDate))
                .toList();

        return new ResponseEntity<>(new PagesResponse(todayTask,getAllListsResponse(),userResponse()),HttpStatus.OK);
    }

    @GetMapping("/all-tasks/sort-by-target-date")
    public ResponseEntity<?> allTasks(){

        var allTasks = getAllTodosResponse()
                .stream()
                .sorted(Comparator.comparing(TodoResponse::getDate))
                .toList();

        return new ResponseEntity<>(new PagesResponse(allTasks,getAllListsResponse(),userResponse()),HttpStatus.OK);
    }

    @GetMapping("/completed-tasks/sort-by-target-date")
    public ResponseEntity<?> completedTasks(){

        var completedTasks = getAllTodosResponse()
                .stream()
                .filter(TodoResponse::isDone)
                .sorted(Comparator.comparing(TodoResponse::getDate))
                .toList();

        return new ResponseEntity<>(new PagesResponse(completedTasks,getAllListsResponse(),userResponse()),HttpStatus.OK);
    }

    @GetMapping("/overdue-tasks/sort-by-target-date")
    public ResponseEntity<?> overdueTasks(){

        var overdueTasks = getAllTodosResponse()
                .stream()
                .filter(todo -> !todo.isDone())
                .filter(todo -> LocalDate.now().isAfter(todo.getDate()))
                .sorted(Comparator.comparing(TodoResponse::getDate))
                .toList();

        return new ResponseEntity<>(new PagesResponse(overdueTasks,getAllListsResponse(),userResponse()),HttpStatus.OK);
    }
}
