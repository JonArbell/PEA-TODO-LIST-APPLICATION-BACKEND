package com.myapp.pea.Services.ListsService;

import com.myapp.pea.Exceptions.TodoItemNotFoundException;
import com.myapp.pea.Exceptions.TodoListNotFoundException;
import com.myapp.pea.Models.Lists;
import com.myapp.pea.Models.Todo;
import com.myapp.pea.Repository.ListsRepo;
import com.myapp.pea.Repository.TodoRepo;
import com.myapp.pea.Services.TodoService.GetTasks;
import com.myapp.pea.Services.AccountService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ListsOperation {

    private final ListsRepo listsRepo;
    private final TodoRepo todoRepo;
    private final UserService userService;
    private final GetLists getLists;
    private final GetTasks getTasks;

    public void createNewList(Lists list){

        Lists create = Lists
                .builder()
                .userId(userService.getId())
                .date(LocalDateTime.now())
                .todos(list.getTodos())
                .listName(list.getListName())
                .build();

        listsRepo.save(create);
    }

    public void deleteList(Long id,boolean deleteTasks) throws TodoListNotFoundException {

        List<Lists> searchList = getLists.allListsDateModified();

        boolean isDelete = false;

        for(Lists list : getLists.allListsDateModified()){

            if(list.getId().equals(id)){

                for(Todo todo : getTasks.allTodoDateModified()){

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

    public void updateListName(Lists update) throws TodoListNotFoundException {

        List<Lists> searchList = getLists.allListsDateModified();

        boolean isUpdated = false;
        for(Lists currentList : searchList){

            if(currentList.getId().equals(update.getId())){
                isUpdated = true;

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
