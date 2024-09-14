package com.myapp.pea.Services.TodoService;

import com.myapp.pea.Exceptions.NotValidDateException;
import com.myapp.pea.Exceptions.TodoItemNotFoundException;
import com.myapp.pea.Exceptions.TodoListNotFoundException;
import com.myapp.pea.Entities.Lists;
import com.myapp.pea.Entities.Todo;
import com.myapp.pea.Repository.ListsRepo;
import com.myapp.pea.Repository.TodoRepo;
import com.myapp.pea.RequestResponseModels.TodoModels.TodoRequest;
import com.myapp.pea.Services.ListsService.GetLists;
import com.myapp.pea.Services.AccountService.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RequiredArgsConstructor
@Service
public class TodoOperationService {

    private final UserService userService;
    private final GetLists getLists;
    private final TodoRepo todoRepo;
    private final ListsRepo listsRepo;

    public Todo findTodoById(Long id){

        var getTodo = todoRepo.findById(id).orElse(null);

        if(getTodo != null && getTodo.getUserId().equals(userService.getId()))
            return getTodo;

        throw new TodoItemNotFoundException("The specified todo item could not be found.");
    }

    public void addNewTodo(@Valid TodoRequest todoRequest){

        if(checkDate(todoRequest.getDate())){

            var lists = listsRepo
                    .findByUserId(userService.getId())
                    .stream()
                    .filter(list -> list.getId().equals(Long.parseLong(todoRequest.getListId())))
                    .findFirst().orElse(null);

            var newTodo = Todo
                    .builder()
                    .title(todoRequest.getTitle())
                    .lists(lists)
                    .done(false)
                    .userId(userService.getId())
                    .date(todoRequest.getDate())
                    .shortDescription(todoRequest.getShortDescription())
                    .dateModified(LocalDateTime.now())
                    .formattedDate(todoRequest
                            .getDate()
                            .format(DateTimeFormatter.ofPattern("MM-dd-yyyy")))
                    .build();

            todoRepo.save(newTodo);
        }

    }

    public boolean checkDate(LocalDate localDate)throws NotValidDateException {

        if(LocalDate.now().isAfter(localDate)){
            throw new NotValidDateException("The date must be in the future.");
        }

        return true;
    }

    public void deleteTodo(Long id) throws TodoItemNotFoundException {

        List<Todo> searchTodo = todoRepo
                .findByUserId(userService.getId());

        var isDeleted = false;

        for (var currentTodo : searchTodo) {

            if (currentTodo.getId().equals(id)) {

                if(currentTodo.getUserId().equals(userService.getId())){
                    todoRepo.deleteById(id);
                    isDeleted = true;
                    break;
                }else{
                    break;
                }
            }
        }
        if(searchTodo.isEmpty()){
            throw new TodoItemNotFoundException("The todo list is currently empty.");
        }else if(!isDeleted){
            throw new TodoItemNotFoundException("The specified todo item could not be found.");
        }
    }

    public void updateTodo(TodoRequest todoRequest) throws TodoItemNotFoundException,TodoListNotFoundException {

        List<Todo> searchTodo = todoRepo
                .findByUserId(userService.getId());

        if(checkDate(todoRequest.getDate())){

            boolean isUpdated = false;

            for(var currentTodo : searchTodo){

                if(todoRequest.getId().equals(currentTodo.getId())){

                    var isCheckList = false;

                    if(todoRequest.getListId() != null && !todoRequest.getListId().equals("0")){
                        for(Lists list : getLists.allListsDateModified()){

                            if(list.getId().equals(Long.parseLong(todoRequest.getListId()))){
                                currentTodo.setLists(list);
                                isCheckList = true;
                                break;
                            }
                        }
                        if(!isCheckList){
                            throw new TodoListNotFoundException("No list found");
                        }
                    }else{
                        currentTodo.setLists(null);
                    }

                    currentTodo.setDate(todoRequest.getDate());
                    currentTodo.setTitle(todoRequest.getTitle());
                    currentTodo.setShortDescription(todoRequest.getShortDescription());
                    currentTodo.setDone(todoRequest.isDone());
                    currentTodo.setDateModified(LocalDateTime.now());
                    currentTodo.setFormattedDate(todoRequest.getDate().format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));

                    todoRepo.save(currentTodo);

                    isUpdated = true;
                    break;
                }

            }

            if(searchTodo.isEmpty()){
                throw new TodoItemNotFoundException("You don't have any todo items yet.");
            }else if(!isUpdated){
                throw new TodoItemNotFoundException("The specified todo item could not be found.");
            }

        }

    }

    public void markAsComplete(Long id) throws TodoItemNotFoundException {

        List<Todo> searchTodo = todoRepo
                .findByUserId(userService.getId());

        var isMarked = false;
        for(var currentTodo : searchTodo){

            if(currentTodo.getId().equals(id)){
                isMarked = true;
                currentTodo.setDone(true);
                currentTodo.setDateModified(LocalDateTime.now());
                todoRepo.save(currentTodo);

                break;
            }

        }

        if(searchTodo.isEmpty()){
            throw new TodoItemNotFoundException("You don't have any todo items yet.");
        }else if(!isMarked){
            throw new TodoItemNotFoundException("The specified todo item could not be found.");
        }

    }
}
