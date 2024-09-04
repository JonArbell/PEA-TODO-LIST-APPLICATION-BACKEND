package com.myapp.pea.Controllers.Authenticated.TopNav;

import com.myapp.pea.Models.Lists;
import com.myapp.pea.Models.Todo;
import com.myapp.pea.Services.ListsService.GetLists;
import com.myapp.pea.Services.TodoService.GetTasks;
import com.myapp.pea.Services.AccountService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@AllArgsConstructor
public class TopNav {

    private final UserService userService;
    private final GetLists getLists;
    private final GetTasks getTasks;

    @GetMapping("/profile/{username}")
    public String getProfile(@PathVariable String username){

        if(username == null){
            System.out.println("Profile : "+username);
            throw new RuntimeException("No Profile");
        }
//        return "profile";
        return "public/underConstruction";
    }

    @GetMapping("/about-us")
    public String aboutUs(ModelMap map){

        modelMap(map);
        return "authenticated/topNav/about";
    }

    @GetMapping("/contact-us")
    public String contactUs(ModelMap map){

        modelMap(map);
        return "authenticated/topNav/contact";
    }

    @GetMapping("/settings")
    public String settings(ModelMap map){
        map.addAttribute("firstname",userService.getFirstName());
        map.addAttribute("lastname",userService.getLastName());
        modelMap(map);
//        return "authenticated/topNav/settings";
        return "public/underConstruction";
    }

    public void modelMap(ModelMap map){
        map.addAttribute("fullname",userService.getFirstName()+" "+userService.getLastName());
        map.addAttribute("username",userService.getUsername());
        map.addAttribute("lists",getLists.allListsDateModified());
        map.addAttribute("todo",new Todo());
        map.addAttribute("list", new Lists());

        double completedTask = getTasks.allTodoDateModified().stream().filter(Todo::isDone).toList().size();
        double divide = completedTask / getTasks.allTodoTargetDate().size();
        int average = (int)(divide * 100);

        map.addAttribute("average",average);
    }

}
