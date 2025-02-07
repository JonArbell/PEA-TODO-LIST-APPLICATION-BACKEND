package com.myapp.pea.Controllers;

import com.myapp.pea.DTO.Request.User.UserRequestDTO;
import com.myapp.pea.DTO.Response.UserResponseDTO;
import com.myapp.pea.Services.User.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RequestMapping("/api")
@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/add-user")
    public ResponseEntity<Map<String, UserResponseDTO>> addUser(@Valid @RequestBody UserRequestDTO user){

        var newUser = userService.addUser(user);

        log.info("New User : {}",newUser);

        return new ResponseEntity<>(Map.of("new-user",newUser), HttpStatus.CREATED);
    }

}
