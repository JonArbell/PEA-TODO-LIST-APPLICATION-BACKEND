package com.myapp.pea.Controllers.CustomComponent;

import com.myapp.pea.Models.Lists;
import com.myapp.pea.Models.Todo;
import com.myapp.pea.Services.Lists.GetLists;
import com.myapp.pea.Services.TodoService.GetTasks;
import com.myapp.pea.Services.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MyCustomModelMap {

    private final UserService userService;
    private final GetLists getLists;
    private final GetTasks getTasks;
    @Getter
    private final StringBuilder currentUrl = new StringBuilder();

    public void modelMap(ModelMap map,
                         List<Todo> todo,
                         String typeOfTask,
                         String typeOfTotal,
                         String request){

        map.addAttribute(typeOfTask,todo);
        map.addAttribute(typeOfTotal,todo.size());
        map.addAttribute("username",userService.getUsername());
        map.addAttribute("fullname",userService.getFirstName()+" "+userService.getLastName());
        map.addAttribute("todo",new Todo());

        map.addAttribute("lists",getLists.allListsDateModified());
        map.addAttribute("list",new Lists());


        double completedTask = getTasks.allTodoDateModified().stream().filter(Todo::isDone).toList().size();
        double divide = completedTask / getTasks.allTodoTargetDate().size();
        int average = (int)(divide * 100);

        map.addAttribute("average",average);

        currentUrl.delete(0,currentUrl.length());
        currentUrl.append(request);

    }


}
