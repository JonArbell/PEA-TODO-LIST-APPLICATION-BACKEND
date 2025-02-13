package com.myapp.pea.Controllers;

import com.myapp.pea.DTO.Request.User.Login.UserRequestLoginDTO;
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

@RequestMapping("/api/authentication")
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
    public ResponseEntity<Map<String, String>> loginTraditional(@Valid @RequestBody UserRequestLoginDTO user){

        var authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),
                user.getPassword()));

        var token = jwtService.generateToken(authentication);

        return new ResponseEntity<>(Map.of("jwtToken",token), HttpStatus.OK);
    }

    @GetMapping("/oauth2/callback")
    public ResponseEntity<Map<String, String>> sendTokenOauth2Callback(@RequestParam String tokenId){

        log.info("Panis : {}",tokenId);

        var token = jwtService.getToken(tokenId);

        log.info("Token : {}",token);

        if(token == null)
            return new ResponseEntity<>(Map.of("error","Token not found."), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(Map.of("jwtToken",token), HttpStatus.CREATED);
    }

}
