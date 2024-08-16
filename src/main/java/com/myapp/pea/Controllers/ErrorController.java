package com.myapp.pea.Controllers;

import com.myapp.pea.Models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/pea")
public class ErrorController {

    @GetMapping("/login?error={error}")
    public String error(@RequestParam boolean error,
                        RedirectAttributes map){

        map.addFlashAttribute("user",new User());
        if(error){
            map.addFlashAttribute("loginFailed","Wrong username or password.");
            return "public/index";
        }
        return "redirect:/pea";
    }

    @GetMapping("/login")
    public String redirect(RedirectAttributes map){

        map.addAttribute("user",new User());
        return "redirect:/pea";
    }

}
