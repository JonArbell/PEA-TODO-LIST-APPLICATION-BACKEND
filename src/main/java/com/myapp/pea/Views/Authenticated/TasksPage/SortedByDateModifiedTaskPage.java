package com.myapp.pea.Views.Authenticated.TasksPage;

import com.myapp.pea.Services.AccountService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class SortedByDateModifiedTaskPage {

    private UserService userService;

    @GetMapping("/home")
    public String home() {

        if(userService.isUserAuthenticated()){
            return "authenticated/main/home";
        }
        return "redirect:/";
    }

    @GetMapping("/todays-tasks")
    public String todayTasks(){
        if(userService.isUserAuthenticated()){
            return "authenticated/tasks/todayTasks";
        }
        return "redirect:/";
    }

    @GetMapping("/completed-tasks")
    public String completedTasks(){
        if(userService.isUserAuthenticated()){
            return "authenticated/tasks/completedTasks";
        }
        return "redirect:/";
    }

    @GetMapping("/all-tasks")
    public String allTasks(){
        if(userService.isUserAuthenticated()){
            return "authenticated/tasks/allTasks";
        }
        return "redirect:/";
    }

    @GetMapping("/overdue-tasks")
    public String overDue(){
        if(userService.isUserAuthenticated()){
            return"authenticated/tasks/overdueTasks";
        }
        return "redirect:/";
    }

}
