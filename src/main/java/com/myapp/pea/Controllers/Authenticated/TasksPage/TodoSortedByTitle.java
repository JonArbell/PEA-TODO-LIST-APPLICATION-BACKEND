package com.myapp.pea.Controllers.Authenticated.TasksPage;

import com.myapp.pea.Controllers.CustomComponent.MyCustomModelMap;
import com.myapp.pea.Models.Todo;
import com.myapp.pea.Services.TodoService.GetTasks;
import com.myapp.pea.Services.AccountService.UserService;
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
public class TodoSortedByTitle {

    private final UserService userService;
    private final GetTasks getTasks;
    private final MyCustomModelMap myCustomModelMap;

    @GetMapping("/home/sort-by-title")
    public String home(ModelMap map, HttpServletRequest request) {

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

        List<Todo> allTasks = getTasks.allTodoTitle();

        myCustomModelMap.modelMap(map,
                allTasks,
                "allTasks",
                "totalOfAllTask",
                request.getRequestURI());
        return "authenticated/tasks/allTasks";
    }

    @GetMapping("/overdue-tasks/sort-by-title")
    public String overDue(ModelMap map,HttpServletRequest request){

        List<Todo> overDue = getTasks
                .allTodoDateModified()
                .stream()
                .filter(todo -> !todo.isDone())
                .filter(todo -> LocalDate.now().isAfter(todo.getDate()))
                .toList();

        myCustomModelMap.modelMap(map,
                overDue,
                "overDueTask",
                "totalOfOverDueTasks",
                request.getRequestURI());

        return"authenticated/tasks/overdueTasks";
    }

}
