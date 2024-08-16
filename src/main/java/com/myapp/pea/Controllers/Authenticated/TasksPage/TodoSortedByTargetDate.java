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
public class TodoSortedByTargetDate {

    private final UserService userService;
    private final GetTasks getTasks;
    private final MyCustomModelMap myCustomModelMap;


    @GetMapping("/home/sort-by-target-date")
    public String home(ModelMap map, HttpServletRequest request) {

        if(!userService.isUserAuthenticated()){
            return "redirect:/pea";
        }
        List<Todo> allTodo = getTasks
                .allTodoTargetDate()
                .stream().filter(todo -> !todo.isDone())
                .toList();

        map.addAttribute("todos",allTodo);

//        modelMap(map,allTodo,"todos","totalOfPending",request.getRequestURI());
        myCustomModelMap.modelMap(map,allTodo,"todos","totalOfPending",request.getRequestURI());

        return "authenticated/main/homePage" ;

    }

    @GetMapping("/todays-tasks/sort-by-target-date")
    public String todayTasks(ModelMap map, HttpServletRequest request){

        if(!userService.isUserAuthenticated()){
            return "redirect:/pea";
        }

        List<Todo> todayTask = getTasks
                .allTodoTargetDate()
                .stream()
                .filter(todo -> LocalDate.now().isEqual(todo.getDate()))
                .filter(todo -> !todo.isDone())
                .toList();

//        modelMap(map,
//                todayTask,
//                "todaysTask",
//                "totalOfPending",
//                request.getRequestURI());

        myCustomModelMap.modelMap(map,todayTask,"todaysTask","totalOfPending",request.getRequestURI());
        return "authenticated/tasks/todayTasks";
    }

    @GetMapping("/completed-tasks/sort-by-target-date")
    public String completedTasks(ModelMap map,HttpServletRequest request){

        if(!userService.isUserAuthenticated()){
            return "redirect:/pea";
        }

        List<Todo> completedTasks = getTasks
                .allTodoTargetDate()
                .stream()
                .filter(Todo::isDone)
                .toList();

//        modelMap(map,
//                completedTasks,
//                "completedTasks",
//                "totalOfCompleted",
//                request.getRequestURI());

        myCustomModelMap.modelMap(map,completedTasks,"completedTasks","totalOfCompleted",request.getRequestURI());
        return "authenticated/tasks/completedTasks";
    }

    @GetMapping("/all-tasks/sort-by-target-date")
    public String allTasks(ModelMap map,HttpServletRequest request){

        if(!userService.isUserAuthenticated()){
            return "redirect:/pea";
        }
        List<Todo> allTasks = getTasks.allTodoTargetDate();

//        modelMap(map,
//                allTasks,
//                "allTasks",
//                "totalOfAllTask",
//                request.getRequestURI());

        myCustomModelMap.modelMap(map,allTasks,"allTasks","totalOfAllTask",request.getRequestURI());
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
//
//    }
}
