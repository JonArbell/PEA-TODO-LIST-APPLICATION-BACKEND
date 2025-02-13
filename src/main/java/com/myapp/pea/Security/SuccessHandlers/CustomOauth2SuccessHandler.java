package com.myapp.pea.Security.SuccessHandlers;

import com.myapp.pea.DTO.Request.User.UserRequestGoogleBaseDTO;
import com.myapp.pea.DTO.Response.User.UserResponseBaseDTO;
import com.myapp.pea.Security.JWT.JwtService;
import com.myapp.pea.Services.User.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomOauth2SuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;
    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        var oauthUser = (OAuth2User) authentication.getPrincipal();

        var email = (String) oauthUser.getAttribute("email");

        if(userService.isEmailNotRegistered(email)){

            var googleId = (String)  oauthUser.getAttribute("sub");

            var fullName = (String) oauthUser.getAttribute("name");

            var addedUser = addNewUser(email, googleId, fullName);

            log.info("Added User Oauth2 : {}",addedUser);
        }

        var tokenId = UUID.randomUUID().toString();

        var token = jwtService.generateToken(authentication);

        jwtService.setToken(tokenId, token);

        log.info("Token : {}",token);

        response.sendRedirect("http://localhost:4200/oauth2/callback?tokenId="+tokenId);
    }

    private UserResponseBaseDTO addNewUser(String email, String googleId, String fullName){
        var newUser = new UserRequestGoogleBaseDTO();
        newUser.setGoogleId(googleId);
        newUser.setFullName(fullName);
        newUser.setEmail(email);

        log.info("New User Oauth2 : {}",newUser);

        return userService.addUserGoogleOath(newUser);
    }

}
