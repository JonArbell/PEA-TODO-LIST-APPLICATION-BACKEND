package com.myapp.pea.Views.CustomComponent;

import com.myapp.pea.Services.AccountService.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyCustomModelMap {

    private final UserService userService;
    @Getter
    private final StringBuilder currentUrl = new StringBuilder();

//    public void modelMap(ModelMap map,
//                         List<Todo> todo,
//                         String typeOfTask,
//                         String typeOfTotal,
//                         String request){
//
//        map.addAttribute(typeOfTask,todo);
//        map.addAttribute(typeOfTotal,todo.size());
//        map.addAttribute("username",userService.getUsername());
//        map.addAttribute("fullname",userService.getFirstName()+" "+userService.getLastName());
//        map.addAttribute("todo",new Todo());
//
//        map.addAttribute("lists",getLists.allListsDateModified());
//        map.addAttribute("list",new Lists());
//
//        double completedTask = getTasks.allTodoDateModified().stream().filter(Todo::isDone).toList().size();
//        double divide = completedTask / getTasks.allTodoTargetDate().size();
//        int average = (int)(divide * 100);
//
//        map.addAttribute("average",average);
//
//        currentUrl.delete(0,currentUrl.length());
//        currentUrl.append(request);
//
//    }


}
