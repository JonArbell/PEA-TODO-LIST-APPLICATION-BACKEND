package com.myapp.pea.Controllers.Authenticated.TasksPage;

import com.myapp.pea.Controllers.CustomComponent.MyCustomModelMap;
import com.myapp.pea.Models.Todo;
import com.myapp.pea.Services.TodoService.GetTasks;
import com.myapp.pea.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.time.LocalDate;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/pea")
public class TodoSortedByTitle {

    private final UserService userService;
    private final GetTasks getTasks;
    private final MyCustomModelMap myCustomModelMap;

    @GetMapping("/home/sort-by-title")
    public String home(ModelMap map, HttpServletRequest request) {

        if(!userService.isUserAuthenticated()){
            return "redirect:/pea";
        }
        List<Todo> allTodo = getTasks
                .allTodoTitle()
                .stream().filter(todo -> !todo.isDone())
                .toList();

        if(!map.containsAttribute("todos")){
            map.addAttribute("todos",allTodo);
        }

        myCustomModelMap.modelMap(map,allTodo,"todos","totalOfPending",request.getRequestURI());

        return "authenticated/main/homePage" ;

    }

    @GetMapping("/todays-tasks/sort-by-title")
    public String todayTasks(ModelMap map, HttpServletRequest request){

        if(!userService.isUserAuthenticated()){
            return "redirect:/pea";
        }

        List<Todo> todayTask = getTasks.allTodoTitle()
                .stream()
                .filter(todo -> LocalDate.now().isEqual(todo.getDate()))
                .filter(todo -> !todo.isDone())
                .toList();

        myCustomModelMap.modelMap(map,
                todayTask,
                "todaysTask",
                "totalOfPending",
                request.getRequestURI());

        return "authenticated/tasks/todayTasks";
    }

    @GetMapping("/completed-tasks/sort-by-title")
    public String completedTasks(ModelMap map,HttpServletRequest request){

        if(!userService.isUserAuthenticated()){
            return "redirect:/pea";
        }

        List<Todo> completedTasks = getTasks.allTodoTitle()
                .stream()
                .filter(Todo::isDone)
                .toList();

        myCustomModelMap.modelMap(map,
                completedTasks,
                "completedTasks",
                "totalOfCompleted",
                request.getRequestURI());

        return "authenticated/tasks/completedTasks";
    }

    @GetMapping("/all-tasks/sort-by-title")
    public String allTasks(ModelMap map,HttpServletRequest request){

        if(!userService.isUserAuthenticated()){
            return "redirect:/pea";
        }
        List<Todo> allTasks = getTasks.allTodoTitle();

//        modelMap(map,
//                allTasks,
//                "allTasks",
//                "totalOfAllTask",
//                request.getRequestURI());

        myCustomModelMap.modelMap(map,
                allTasks,
                "allTasks",
                "totalOfAllTask",
                request.getRequestURI());
        return "authenticated/tasks/allTasks";
    }

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
//
//        sortedByDateModified.currentUrl.delete(0,sortedByDateModified.currentUrl.length());
//        sortedByDateModified.currentUrl.append(request);
//
//    }

}
