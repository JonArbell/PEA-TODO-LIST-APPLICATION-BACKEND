package com.myapp.pea.Security.JWT.CustomJwtAuthFilter;

import com.myapp.pea.Security.JWT.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
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

        var header = request.getHeader("Authorization");

        if(header == null || !header.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        var token = header.substring(7);
        try{

            var username = jwtService.extractUsername(token);

            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                var userDetails = userDetailsService.loadUserByUsername(username);

                if(userDetails != null && jwtService.isTokenValid(token)){
                    var usnPassAuthToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    SecurityContextHolder.getContext().setAuthentication(usnPassAuthToken);
                    logger.info("Token : {}",token);
                    logger.info("Username : {}",username);
                }
            }
        }catch (JwtException e){
            logger.error("JwtException : {}",e.getMessage());
        }catch (Exception e){
            logger.error("Exception : {}",e.getMessage());
        }

        filterChain.doFilter(request,response);

    }
}
