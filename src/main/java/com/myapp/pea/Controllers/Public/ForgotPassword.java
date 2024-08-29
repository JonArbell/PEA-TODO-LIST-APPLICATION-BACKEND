package com.myapp.pea.Controllers.Public;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ForgotPassword {
    @GetMapping("/forgot-password")
    public String forgotPassGet(){
//        return "public/forgotPasswordPage";
        return "public/underConstruction";
    }

    @PostMapping("/forgot-password")
    public String forgotPassPost(){
//        return "public/forgotPasswordPage";
        return "public/underConstruction";
    }

}
