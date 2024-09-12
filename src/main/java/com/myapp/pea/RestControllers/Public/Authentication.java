package com.myapp.pea.RestControllers.Public;

import com.myapp.pea.Entities.User;
import com.myapp.pea.RequestResponseModels.JwtModels.JwtRequest;
import com.myapp.pea.RequestResponseModels.JwtModels.JwtResponse;
import com.myapp.pea.Security.JWT.JwtService;
import com.myapp.pea.Services.AccountService.CreateAccountService;
import jakarta.servlet.http.HttpServletResponse;
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
public class Authentication {

    private final Logger logger = LoggerFactory.getLogger(Authentication.class);
    private final CreateAccountService createAccountService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody JwtRequest jwtRequest, BindingResult bindingResult,
                                   HttpServletResponse response){

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

            response.setHeader("Set-Cookie",
                    "jwtAuthToken=" + token + "; HttpOnly; Secure; SameSite=None; Path=/; Max-Age=3600");

            return new ResponseEntity<>(new JwtResponse(token),HttpStatus.OK);
        }catch (AuthenticationException e){
            logger.error("Authentication Error : {}",e.getMessage());
            errors.put("error","Invalid username or password");
            return new ResponseEntity<>(errors,HttpStatus.FORBIDDEN);
        }catch (Exception e){
            logger.error("Exception Error : {}",e.getMessage());
            errors.put("error",e.getMessage());
            return new ResponseEntity<>(errors,HttpStatus.FORBIDDEN);
        }

    }

    @PostMapping("/create-account")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user,BindingResult bindingResult){

        Map<Object,Object> errors = new HashMap<>();
        if(bindingResult.hasErrors()){
            bindingResult.getFieldErrors()
                    .forEach(error ->
                            errors.put(error.getField(),error.getDefaultMessage())
                    );
            logger.error("Error : {}",errors);
            return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        }

        try{

            createAccountService.createAccount(user);
            logger.info("User : {}",user);
            return new ResponseEntity<>(user,HttpStatus.CREATED);
        }catch (Exception e){
            logger.error("Exception : {}",e.getMessage());
            errors.put("error",e.getMessage());
        }

        return new ResponseEntity<>(errors,HttpStatus.FORBIDDEN);
    }

}
