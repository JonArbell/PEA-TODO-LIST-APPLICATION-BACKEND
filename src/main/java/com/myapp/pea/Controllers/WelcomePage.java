package com.myapp.pea.Controllers;

import com.myapp.pea.Models.User;
import com.myapp.pea.Services.UserService;
import com.myapp.pea.Services.CreateAccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Objects;

@AllArgsConstructor
@Controller
@RequestMapping("/pea")
public class WelcomePage {

    private final CreateAccountService createAccountService;
    private final UserService userService;

    @GetMapping("")
    public String welcomePage(ModelMap map){

        if(userService.isUserAuthenticated()){
            return "redirect:/pea/home";
        }

        map.addAttribute("user",new User());
        return "public/index";
    }

    @PostMapping("/create-account")
    public String createAccount(@Valid User newUser,
                                BindingResult result,
                                RedirectAttributes map){

        System.out.println("New user : "+newUser);
        try {

            if(result.hasErrors()){

                String error = Objects.requireNonNull(result.getFieldError()).getDefaultMessage();
                System.out.println("Error : "+error);
                map.addFlashAttribute("createAccMessage",error);
            }else{
                createAccountService.createAccount(newUser);
                map.addFlashAttribute("createAccMessage","success");
            }

        }catch (Exception e){
            map.addFlashAttribute("createAccMessage",e.getMessage());
            System.out.println("Error : "+e.getMessage());
        }

        return "redirect:/pea";
    }

    @GetMapping("/lopit")
    public String lopit(){

        return "authenticated/main/try";

    }

}