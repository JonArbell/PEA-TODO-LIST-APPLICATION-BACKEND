package com.myapp.pea.Controllers;

import com.myapp.pea.DTO.Request.UserRequestDTO;
import com.myapp.pea.DTO.Response.UserResponseDTO;
import com.myapp.pea.Services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/add-user")
    public ResponseEntity<Map<String, UserResponseDTO>> testAddUser(@Valid @RequestBody UserRequestDTO user){

        var newUser = userService.setUser(user);

        log.info("New User : {}",newUser);

        return new ResponseEntity<>(Map.of("response",newUser), HttpStatus.OK);
    }

}
