package com.myapp.pea.Views.Authenticated.TasksPage;

import com.myapp.pea.Views.CustomComponent.MyCustomModelMap;
import com.myapp.pea.Entities.Todo;
import com.myapp.pea.Services.TodoService.GetTasks;
import com.myapp.pea.Services.AccountService.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@AllArgsConstructor
public class TodoSortedByTargetDate {

    private final UserService userService;
    private final GetTasks getTasks;
    private final MyCustomModelMap myCustomModelMap;


    @GetMapping("/home/sort-by-target-date")
    public String home(ModelMap map, HttpServletRequest request) {

        List<Todo> allTodo = getTasks
                .allTodoTargetDate()
                .stream().filter(todo -> !todo.isDone())
                .toList();

        map.addAttribute("todos",allTodo);

        myCustomModelMap.modelMap(map,allTodo,"todos","totalOfPending",request.getRequestURI());

        return "authenticated/main/homePage" ;

    }

    @GetMapping("/todays-tasks/sort-by-target-date")
    public String todayTasks(ModelMap map, HttpServletRequest request){

        List<Todo> todayTask = getTasks
                .allTodoTargetDate()
                .stream()
                .filter(todo -> LocalDate.now().isEqual(todo.getDate()))
                .filter(todo -> !todo.isDone())
                .toList();

        myCustomModelMap.modelMap(map,todayTask,"todaysTask","totalOfPending",request.getRequestURI());
        return "authenticated/tasks/todayTasks";
    }

    @GetMapping("/completed-tasks/sort-by-target-date")
    public String completedTasks(ModelMap map,HttpServletRequest request){

        List<Todo> completedTasks = getTasks
                .allTodoTargetDate()
                .stream()
                .filter(Todo::isDone)
                .toList();

        myCustomModelMap.modelMap(map,completedTasks,"completedTasks","totalOfCompleted",request.getRequestURI());
        return "authenticated/tasks/completedTasks";
    }

    @GetMapping("/all-tasks/sort-by-target-date")
    public String allTasks(ModelMap map,HttpServletRequest request){

        List<Todo> allTasks = getTasks.allTodoTargetDate();

        myCustomModelMap.modelMap(map,allTasks,"allTasks","totalOfAllTask",request.getRequestURI());
        return "authenticated/tasks/allTasks";
    }

    @GetMapping("/overdue-tasks/sort-by-target-date")
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
