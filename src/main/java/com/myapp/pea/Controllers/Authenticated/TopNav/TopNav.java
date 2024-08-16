package com.myapp.pea.Controllers.Authenticated.TopNav;

import com.myapp.pea.Models.Todo;
import com.myapp.pea.Models.User;
import com.myapp.pea.Services.Lists.GetLists;
import com.myapp.pea.Services.TodoService.GetTasks;
import com.myapp.pea.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@AllArgsConstructor
public class TopNav {

    private final UserService userService;
    private final GetLists getLists;
    private final GetTasks getTasks;

    @GetMapping("/{profile}")
    public String getProfile(@ModelAttribute User profile){

        return "profile";
    }

    @GetMapping("/about-us")
    public String aboutUs(ModelMap map){

        if(!userService.isUserAuthenticated()){
            return "redirect:/pea";
        }
        modelMap(map);
        return "authenticated/topNav/about";
    }

    @GetMapping("/contact-us")
    public String contactUs(ModelMap map){

        if(!userService.isUserAuthenticated()){
            return "redirect:/pea";
        }

        modelMap(map);
        return "authenticated/topNav/contact";
    }

    public void modelMap(ModelMap map){
        map.addAttribute("fullname",userService.getFirstName()+" "+userService.getLastName());
        map.addAttribute("username",userService.getUsername());
        map.addAttribute("todo",new Todo());
        map.addAttribute("lists",getLists.allListsDateModified());
        
        double completedTask = getTasks.allTodoDateModified().stream().filter(Todo::isDone).toList().size();
        double divide = completedTask / getTasks.allTodoTargetDate().size();
        int average = (int)(divide * 100);

        map.addAttribute("average",average);
    }

}
