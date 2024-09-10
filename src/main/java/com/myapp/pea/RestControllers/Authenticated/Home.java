package com.myapp.pea.RestControllers.Authenticated;

import com.myapp.pea.Security.JWT.JwtModels.JwtResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

    @GetMapping("/home")
    public ResponseEntity<?> home(){

        return new ResponseEntity<>(new JwtResponse("Panis nasa Home ka na"),HttpStatus.OK);
    }

}
