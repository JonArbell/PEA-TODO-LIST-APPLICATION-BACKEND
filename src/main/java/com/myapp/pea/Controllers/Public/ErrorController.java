package com.myapp.pea.Controllers.Public;

import com.myapp.pea.Models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ErrorController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        ModelMap map) {

        if (error != null) {
            map.addAttribute("loginFailedMessage", "Invalid username or password");
        }

        map.addAttribute("user",new User());

        return "public/index";
    }

}
