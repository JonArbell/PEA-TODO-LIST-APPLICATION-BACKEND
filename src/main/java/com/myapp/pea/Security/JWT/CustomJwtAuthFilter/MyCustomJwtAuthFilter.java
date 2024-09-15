package com.myapp.pea.Security.JWT.CustomJwtAuthFilter;

import com.myapp.pea.Security.JWT.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;


@Configuration
@AllArgsConstructor
public class MyCustomJwtAuthFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final Logger logger = LoggerFactory.getLogger(MyCustomJwtAuthFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var cookies = request.getCookies();

        if(cookies == null){
            filterChain.doFilter(request,response);
            return;
        }

        for(var cookie : cookies){

            if(cookie.getName().equals("jwtAuthToken")){
                logger.info("Processing cookie: {} with value: {}", cookie.getName(), cookie.getValue());

                try{
                    var username = jwtService.extractUsername(cookie.getValue());
                    logger.info("Username: {}",username);

                    if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                        var userDetails = userDetailsService.loadUserByUsername(username);

                        logger.info("Is token valid ? : {}",jwtService.isTokenValid(cookie.getValue()));
                        if (userDetails != null && jwtService.isTokenValid(cookie.getValue())) {

                            logger.info("Password : {}",userDetails.getPassword());

                            var usnPassAuthToken = new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );
                            SecurityContextHolder.getContext().setAuthentication(usnPassAuthToken);
                        }else{
                            logger.warn("Token validation failed for user: {}", username);
                        }

                    }else {
                        logger.warn("Username is null or authentication is already set.");
                    }
                }catch (JwtException e){
                    logger.error("JwtException : {}",e.getMessage());
                }catch (Exception e){
                    logger.error("Exception : {}",e.getMessage());
                }
                break;
            }
        }

        filterChain.doFilter(request,response);
    }
}
