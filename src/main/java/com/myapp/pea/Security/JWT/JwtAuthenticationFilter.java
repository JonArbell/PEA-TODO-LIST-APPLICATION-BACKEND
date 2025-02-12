package com.myapp.pea.Security.JWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var header = request.getHeader("Authorization");

        if(header != null && header.startsWith("Bearer ")){

            var token = header.substring(7);

            try{
                var email = jwtService.extractEmail(token);

                log.info("Email : {}",email);

                if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){

                    if(jwtService.validateToken(token, email)){

                        var userDetails = userDetailsService.loadUserByUsername(email) ;

                        var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                        SecurityContextHolder.getContext().setAuthentication(authToken);

                    }
                }
            }catch (Exception e){
                log.error("Invalid JWT Token: {}", e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }
}
