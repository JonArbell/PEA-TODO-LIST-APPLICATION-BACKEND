package com.myapp.pea.Views.Authenticated.NavView;

import com.myapp.pea.Services.AccountService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@AllArgsConstructor
public class NavPagesView {

    private final UserService userService;

    @GetMapping("/profile/{username}")
    public String getProfile(@PathVariable String username){

        if(!userService.isUserAuthenticated()){
            return "redirect:/";
        }

        if(username == null){
            throw new RuntimeException("No Profile");
        }
//        return "profile";
        return "public/underConstruction";
    }

    @GetMapping("/about-us")
    public String aboutUs(){
        if(!userService.isUserAuthenticated()){
            return "redirect:/";
        }
        return "authenticated/Nav/about";
    }

    @GetMapping("/contact-us")
    public String contactUs(){
        if(!userService.isUserAuthenticated()){
            return "redirect:/";
        }
        return "authenticated/Nav/contact";
    }

    @GetMapping("/settings")
    public String settings(){
        if(!userService.isUserAuthenticated()){
            return "redirect:/";
        }
        return "public/underConstruction";
    }

//    public void modelMap(ModelMap map){
//        map.addAttribute("fullname",userService.getFirstName()+" "+userService.getLastName());
//        map.addAttribute("username",userService.getUsername());
//        map.addAttribute("lists",getLists.allListsDateModified());
//        map.addAttribute("todo",new Todo());
//        map.addAttribute("list", new Lists());
//
//        double completedTask = getTasks.allTodoDateModified().stream().filter(Todo::isDone).toList().size();
//        double divide = completedTask / getTasks.allTodoTargetDate().size();
//        int average = (int)(divide * 100);
//
//        map.addAttribute("average",average);
//    }

}
