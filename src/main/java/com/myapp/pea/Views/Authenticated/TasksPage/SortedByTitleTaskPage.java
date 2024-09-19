package com.myapp.pea.Views.Authenticated.TasksPage;

import com.myapp.pea.Services.AccountService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class SortedByTitleTaskPage {

    private final UserService userService;

    @GetMapping("/home/sort-by-title")
    public String home() {

        if(!userService.isUserAuthenticated())
            return "redirect:/";

        return "authenticated/main/home";

    }

    @GetMapping("/todays-tasks/sort-by-title")
    public String todayTasks(){
        if(!userService.isUserAuthenticated())
            return "redirect:/";

        return "authenticated/tasks/todayTasks";
    }

    @GetMapping("/completed-tasks/sort-by-title")
    public String completedTasks(){

        if(!userService.isUserAuthenticated())
            return "redirect:/";

        return "authenticated/tasks/completedTasks";
    }

    @GetMapping("/all-tasks/sort-by-title")
    public String allTasks(){

        if(!userService.isUserAuthenticated())
            return "redirect:/";

        return "authenticated/tasks/allTasks";
    }

    @GetMapping("/overdue-tasks/sort-by-title")
    public String overDue(){

        if(!userService.isUserAuthenticated())
            return "redirect:/";

        return"authenticated/tasks/overdueTasks";
    }

}
