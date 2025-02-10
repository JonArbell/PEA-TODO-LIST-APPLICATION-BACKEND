package com.myapp.pea.Security.Oauth2;

import com.myapp.pea.DTO.Request.User.UserRequestGoogleBaseDTO;
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

@Slf4j
@RequiredArgsConstructor
@Component
public class Oauth2CustomSuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;
    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        var oauthUser = (OAuth2User) authentication.getPrincipal();

        var email = (String) oauthUser.getAttribute("email");

        if(!userService.findByEmail(email)){

            var googleId = (String)  oauthUser.getAttribute("sub");

            var fullName = (String) oauthUser.getAttribute("name");

            var newUser = new UserRequestGoogleBaseDTO();
            newUser.setGoogleId(googleId);
            newUser.setFullName(fullName);
            newUser.setEmail(email);

            log.info("New User Oauth2 : {}",newUser);

            var added = userService.addUserGoogleOath(newUser);

            log.info("Added User Oauth2 : {}",added);
        }

        var token = "Panis sobra";

        // Send JWT token in response (frontend can store in localStorage/cookie)
        response.setContentType("application/json");
        response.getWriter().write("{\"token\": \"" + token + "\"}");

    }
}
