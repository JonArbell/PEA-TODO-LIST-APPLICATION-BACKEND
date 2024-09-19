package com.myapp.pea.Views.Authenticated.TasksPage;

import com.myapp.pea.Services.AccountService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@AllArgsConstructor
public class SortedByTargetDateTaskPage {

    private final UserService userService;

    @GetMapping("/home/sort-by-target-date")
    public String home() {

        if(!userService.isUserAuthenticated())
            return "redirect:/";

        return "authenticated/main/home" ;

    }

    @GetMapping("/todays-tasks/sort-by-target-date")
    public String todayTasks(){

        if(!userService.isUserAuthenticated())
            return "redirect:/";

        return "authenticated/tasks/todayTasks";
    }

    @GetMapping("/completed-tasks/sort-by-target-date")
    public String completedTasks(){

        if(!userService.isUserAuthenticated())
            return "redirect:/";

        return "authenticated/tasks/completedTasks";
    }

    @GetMapping("/all-tasks/sort-by-target-date")
    public String allTasks(){

        if(!userService.isUserAuthenticated())
            return "redirect:/";

        return "authenticated/tasks/allTasks";
    }

    @GetMapping("/overdue-tasks/sort-by-target-date")
    public String overDue(){

        if(!userService.isUserAuthenticated())
            return "redirect:/";

        return"authenticated/tasks/overdueTasks";
    }

}
