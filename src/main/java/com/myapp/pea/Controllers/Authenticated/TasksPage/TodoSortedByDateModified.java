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
public class TodoSortedByDateModified {

    private final UserService userService;
    private final GetTasks getTasks;
    private final MyCustomModelMap myCustomModelMap;

    @GetMapping("/home")
    public String home(ModelMap map, HttpServletRequest request) {

        if(!userService.isUserAuthenticated()){
            return "redirect:/pea";
        }
        List<Todo> allTodo = getTasks
                .allTodoDateModified()
                .stream().filter(todo -> !todo.isDone())
                .toList();

        myCustomModelMap.modelMap(map,allTodo,"todos","totalOfPending",request.getRequestURI());


        return "authenticated/main/homePage" ;

    }

    @GetMapping("/todays-tasks")
    public String todayTasks(ModelMap map, HttpServletRequest request){

        if(!userService.isUserAuthenticated()){
            return "redirect:/pea";
        }

        List<Todo> todayTask = getTasks.allTodoDateModified()
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

    @GetMapping("/completed-tasks")
    public String completedTasks(ModelMap map,HttpServletRequest request){

        if(!userService.isUserAuthenticated()){
            return "redirect:/pea";
        }

        List<Todo> completedTasks = getTasks.allTodoDateModified()
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

    @GetMapping("/all-tasks")
    public String allTasks(ModelMap map,HttpServletRequest request){

        if(!userService.isUserAuthenticated()){
            return "redirect:/pea";
        }
        List<Todo> allTasks = getTasks.allTodoDateModified();

        myCustomModelMap.modelMap(map,
                allTasks,
                "allTasks",
                "totalOfAllTask",
                request.getRequestURI());
        return "authenticated/tasks/allTasks";
    }

}
