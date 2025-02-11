package com.myapp.pea.Controllers;

import com.myapp.pea.DTO.Response.User.UserResponseBaseDTO;
import com.myapp.pea.Services.User.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/authenticated")
@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<Map<String, List<? extends UserResponseBaseDTO>>> getAllUsers(){

        return new ResponseEntity<>(Map.of("panis", userService.getAllUsers()), HttpStatus.OK);
    }

}
