package com.myapp.pea.Views.Authenticated.TasksPage;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class WebPagesView {

    @GetMapping("/home")
    public String home() {
        return "authenticated/main/home";
    }

    @GetMapping("/todays-tasks")
    public String todayTasks(){
        return "authenticated/tasks/todayTasks";
    }

    @GetMapping("/completed-tasks")
    public String completedTasks(){
        return "authenticated/tasks/completedTasks";
    }

    @GetMapping("/all-tasks")
    public String allTasks(){
        return "authenticated/tasks/allTasks";
    }

    @GetMapping("/overdue-tasks")
    public String overDue(){
        return"authenticated/tasks/overdueTasks";
    }

}
