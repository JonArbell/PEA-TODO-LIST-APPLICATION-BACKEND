package com.myapp.pea.Services.ListsService;

import com.myapp.pea.Exceptions.TodoItemsNotFoundException;
import com.myapp.pea.Exceptions.TodoListNotFoundException;
import com.myapp.pea.Entities.Lists;
import com.myapp.pea.Repository.ListsRepo;
import com.myapp.pea.Repository.TodoRepo;
import com.myapp.pea.RequestResponseModels.ListsModels.ListsRequest;
import com.myapp.pea.Services.AccountService.UserService;
import com.myapp.pea.Services.TodoService.GetTodos;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ListsOperationService {

    private final ListsRepo listsRepo;
    private final TodoRepo todoRepo;
    private final UserService userService;
    private final GetTodos getTodos;

    public void createNewList(ListsRequest listsRequest){

        var create = Lists
                .builder()
                .userId(userService.getId())
                .date(LocalDateTime.now())
                .listName(listsRequest.getListName())
                .build();

        listsRepo.save(create);
    }

    public void deleteList(Long id,boolean deleteTasks) throws TodoListNotFoundException {

        var searchList = listsRepo.findByUserId(userService.getId());

        boolean isDelete = false;

        for(var list : searchList){

            if(list.getId().equals(id)){

                for(var todo : getTodos.getAllTodo()){

                    if(todo.getLists() != null && todo.getLists().getId().equals(id)){
                        todo.setLists(null);
                        todoRepo.save(todo);

                        if(deleteTasks){
                            todoRepo.deleteById(todo.getId());
                        }

                    }
                }

                listsRepo.deleteById(id);
                isDelete = true;
                break;
            }

        }

        if(searchList.isEmpty()){
            throw new TodoListNotFoundException("You don't have any lists currently.");
        }else if(!isDelete){
            throw new TodoListNotFoundException("The specified todo list name could not be found.");
        }

    }

    public void updateListName(ListsRequest listsRequest) throws TodoListNotFoundException {

        var searchList = listsRepo.findByUserId(userService.getId());

        boolean isUpdated = false;
        for(var currentList : searchList){

            if(currentList.getId().equals(listsRequest.getId())){

                currentList.setListName(listsRequest.getListName());

                listsRepo.save(currentList);

                isUpdated = true;
                break;
            }

        }

        if(searchList.isEmpty()){
            throw new TodoItemsNotFoundException("You don't have any lists currently.");
        }else if(!isUpdated){
            throw new TodoListNotFoundException("The specified todo list name could not be found.");
        }

    }

}
