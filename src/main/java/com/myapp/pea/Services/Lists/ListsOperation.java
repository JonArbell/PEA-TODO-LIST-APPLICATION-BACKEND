package com.myapp.pea.Services.Lists;

import com.myapp.pea.Exceptions.TodoItemNotFoundException;
import com.myapp.pea.Exceptions.TodoListNotFoundException;
import com.myapp.pea.Models.Lists;
import com.myapp.pea.Models.Todo;
import com.myapp.pea.Models.User;
import com.myapp.pea.Repository.ListsRepo;
import com.myapp.pea.Repository.TodoRepo;
import com.myapp.pea.Repository.UsersRepo;
import com.myapp.pea.Services.TodoService.GetTasks;
import com.myapp.pea.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ListsOperation {

    private final ListsRepo listsRepo;
    private TodoRepo todoRepo;
    private final UserService userService;
    private final GetLists getLists;
    private final GetTasks getTasks;
    private final UsersRepo usersRepo;

    public void createNewList(Lists create){

        create.setDate(LocalDateTime.now());
        create.setUserId(userService.getId());

        listsRepo.save(create);
    }

    public void deleteList(Long id) throws TodoListNotFoundException {

        List<Lists> searchList = getLists.allListsDateModified();

        boolean isDelete = false;

        for(Lists currentList : searchList){

            if(currentList.getId().equals(id)){
                isDelete = true;

                for(Todo todo : getTasks.allTodoTargetDate()){

                    if(todo.getLists().getId().equals(id)){
                        todo.setLists(null);
                        todoRepo.save(todo);
                    }
                }
                listsRepo.deleteById(id);
                break;
            }

        }

        if(searchList.isEmpty()){
            throw new TodoListNotFoundException("You don't have any lists currently.");
        }else if(!isDelete){
            throw new TodoListNotFoundException("The specified todo list name could not be found.");
        }

    }

    public void updateList(Lists update) throws TodoListNotFoundException {

        List<Lists> searchList = listsRepo
                .findByUserId(userService.getId())
                .stream()
                .toList();

        boolean isUpdated = false;
        for(Lists currentList : searchList){

            if(currentList.getId().equals(update.getId())){
                isUpdated = true;

                User user = usersRepo.findByUsername(userService.getUsername());
                currentList.setUser(user);
                currentList.setListName(update.getListName());

                listsRepo.save(currentList);
                break;
            }

        }

        if(searchList.isEmpty()){
            throw new TodoItemNotFoundException("You don't have any lists currently.");
        }else if(!isUpdated){
            throw new TodoListNotFoundException("The specified todo list name could not be found.");
        }

    }



}
