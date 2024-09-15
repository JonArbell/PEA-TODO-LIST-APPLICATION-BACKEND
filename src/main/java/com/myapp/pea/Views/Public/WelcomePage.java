package com.myapp.pea.Views.Public;

import com.myapp.pea.Services.AccountService.UserService;
import com.myapp.pea.Services.AccountService.CreateAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class WelcomePage {

    private final CreateAccountService createAccountService;
    private final UserService userService;

    @GetMapping("")
    public String index(){

        if(userService.isUserAuthenticated()){
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/create-account")
    public String getCreateAccount(){
        return "redirect:/";
    }

}