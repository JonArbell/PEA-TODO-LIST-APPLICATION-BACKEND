package com.myapp.pea.Controllers;

import com.myapp.pea.DTO.Request.User.UserRequestTraditionalDTO;
import com.myapp.pea.DTO.Response.User.UserResponseBaseDTO;
import com.myapp.pea.Security.JWT.JwtService;
import com.myapp.pea.Services.User.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RequestMapping("/api/auth")
@Slf4j
@RequiredArgsConstructor
@RestController
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationManager manager;
    private final JwtService jwtService;

    @PostMapping("/add-user")
    public ResponseEntity<Map<String, UserResponseBaseDTO>> addUserTraditional(@Valid @RequestBody UserRequestTraditionalDTO user){

        var newUser = userService.addUserTraditional(user);

        log.info("New User traditional based : {}", newUser);

        return new ResponseEntity<>(Map.of("new-user",newUser), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginTraditional(@Valid @RequestBody UserRequestTraditionalDTO user){

        manager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

//        var token = jwtService.generateToken(user.getEmail());

        return new ResponseEntity<>(Map.of("logged-in-token","Panis token"), HttpStatus.CREATED);
    }

}
