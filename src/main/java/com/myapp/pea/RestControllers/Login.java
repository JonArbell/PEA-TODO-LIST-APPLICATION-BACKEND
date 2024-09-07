package com.myapp.pea.RestControllers;

import com.myapp.pea.Security.JWT.JwtModels.JwtRequest;
import com.myapp.pea.Security.JWT.JwtModels.JwtResponse;
import com.myapp.pea.Security.JWT.JwtService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class Login {

    private final Logger logger = LoggerFactory.getLogger(Login.class);
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody JwtRequest jwtRequest, BindingResult bindingResult){

        logger.info("Username : {}",jwtRequest.getUsername());
        logger.info("Password : {}",jwtRequest.getPassword());

        Map<String,Object> errors = new HashMap<>();
        if(bindingResult.hasErrors()){

            bindingResult.getFieldErrors()
                        .forEach(error ->
                                errors.put(error.getField(),error.getDefaultMessage())
            );

            return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        }

        try{
            var auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    jwtRequest.getUsername(),
                    jwtRequest.getPassword()
            ));

            var token = jwtService.generateToken(auth);

            return new ResponseEntity<>(new JwtResponse(token),HttpStatus.OK);
        }catch (AuthenticationException e){
            logger.error("Authentication Error : {}",e.getMessage());
            errors.put("Error","Invalid username or password");
            return new ResponseEntity<>(errors,HttpStatus.FORBIDDEN);
        }

    }

}
