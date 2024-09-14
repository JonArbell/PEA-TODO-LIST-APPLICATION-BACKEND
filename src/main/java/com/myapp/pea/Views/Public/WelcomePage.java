package com.myapp.pea.Views.Public;

import com.myapp.pea.Services.AccountService.UserService;
import com.myapp.pea.Services.AccountService.CreateAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@AllArgsConstructor
@Controller
public class WelcomePage {

    private final CreateAccountService createAccountService;
    private final UserService userService;

    @GetMapping("")
    public String welcomePage(){
        return "index";
    }

    @GetMapping("/create-account")
    public String getCreateAccount(){
        return "redirect:/";
    }

//    @PostMapping("/create-account")
//    public String createAccount(@Valid User newUser,
//                                BindingResult result,
//                                RedirectAttributes map){
//
//        System.out.println("New user : "+newUser);
//        try {
//
//            if(result.hasErrors()){
//
//                String error = Objects.requireNonNull(result.getFieldError()).getDefaultMessage();
//                System.out.println("Error : "+error);
//                map.addFlashAttribute("createAccMessage",error);
//            }else{
//                createAccountService.createAccount(newUser);
//                map.addFlashAttribute("createAccMessage","success");
//            }
//
//        }catch (Exception e){
//            map.addFlashAttribute("createAccMessage",e.getMessage());
//            System.out.println("Error : "+e.getMessage());
//        }
//
//        return "redirect:/";
//    }

    @GetMapping("/logout-success")
    public String logoutSuccess(RedirectAttributes map){
        map.addFlashAttribute("logoutMessage","You have been logged out successfully!");
        return "redirect:/";
    }

}