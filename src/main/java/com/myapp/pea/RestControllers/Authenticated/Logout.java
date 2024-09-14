package com.myapp.pea.RestControllers.Authenticated;

import com.myapp.pea.RequestResponseModels.JwtModels.JwtResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Logout {

    private final Logger logger = LoggerFactory.getLogger(Logout.class);

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse httpServletResponse){

//        Cookie cookie = new Cookie("jwtAuthToken", null);
//        cookie.setHttpOnly(true);
//        cookie.setSecure(true);
//        cookie.setPath("/");
//        cookie.setMaxAge(0);
//
//        httpServletResponse.addCookie(cookie);
        logger.info("Successfully Logout");
        return new ResponseEntity<>(new JwtResponse("Panis"),HttpStatus.OK);
    }

}
