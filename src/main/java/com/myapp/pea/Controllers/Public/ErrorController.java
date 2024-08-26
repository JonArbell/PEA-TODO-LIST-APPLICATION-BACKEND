package com.myapp.pea.Controllers.Public;

import com.myapp.pea.Models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ErrorController {

    @GetMapping("/login?error={error}")
    public String error(@RequestParam boolean error,
                        RedirectAttributes map){

        map.addFlashAttribute("user",new User());
        if(error){
            map.addFlashAttribute("loginFailed","Wrong username or password.");
            return "public/index";
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String redirect(RedirectAttributes map){

        map.addAttribute("user",new User());
        return "redirect:/";
    }

}
