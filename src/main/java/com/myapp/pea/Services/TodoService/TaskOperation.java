package com.myapp.pea.Services.TodoService;

import com.myapp.pea.Exceptions.NotValidDateException;
import com.myapp.pea.Exceptions.TodoItemNotFoundException;
import com.myapp.pea.Exceptions.TodoListNotFoundException;
import com.myapp.pea.Models.Lists;
import com.myapp.pea.Models.Todo;
import com.myapp.pea.Models.User;
import com.myapp.pea.Repository.TodoRepo;
import com.myapp.pea.Repository.UsersRepo;
import com.myapp.pea.Services.Lists.GetLists;
import com.myapp.pea.Services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskOperation {

    private final UserService userService;
    private final GetLists getLists;
    private final TodoRepo todoRepo;


    public void addNewTodo(@Valid Todo todo){

        if(checkDate(todo.getDate())){

            var newTodo = Todo
                    .builder()
                    .title(todo.getTitle())
                    .lists(todo.getLists())
                    .done(false)
                    .userId(userService.getId())
                    .date(todo.getDate())
                    .shortDescription(todo.getShortDescription())
                    .dateModified(LocalDateTime.now())
                    .formattedDate(todo
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

    public void updateTodo(Todo update) throws TodoItemNotFoundException,TodoListNotFoundException {

        List<Todo> searchTodo = todoRepo
                .findByUserId(userService.getId());

        if(checkDate(update.getDate())){

            boolean isUpdated = false;

            for(var currentTodo : searchTodo){

                if(update.getId().equals(currentTodo.getId())){

                    var isCheckList = false;

                    if(update.getLists() != null){
                        for(Lists list : getLists.allListsDateModified()){

                            if(list.getId().equals(update.getLists().getId())){
                                currentTodo.setLists(update.getLists());
                                isCheckList = true;
                                break;
                            }

                        }
                        if(!isCheckList){
                            throw new TodoListNotFoundException("No list found");
                        }
                    }

                    currentTodo.setLists(update.getLists());
                    currentTodo.setDate(update.getDate());
                    currentTodo.setTitle(update.getTitle());
                    currentTodo.setShortDescription(update.getShortDescription());
                    currentTodo.setDone(update.isDone());
                    currentTodo.setDateModified(LocalDateTime.now());
                    currentTodo.setFormattedDate(update.getDate().format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));

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
