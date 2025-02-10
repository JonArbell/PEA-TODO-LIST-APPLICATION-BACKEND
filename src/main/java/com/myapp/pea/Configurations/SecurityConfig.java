package com.myapp.pea.Configurations;

import com.myapp.pea.Security.JWT.JwtAuthenticationFilter;
import com.myapp.pea.Security.Oauth2.Oauth2CustomSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig{

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final Oauth2CustomSuccessHandler oauth2CustomSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // Allow full access to H2 Console
                        .requestMatchers("/oauth2/**", "/oauth2/authorization/**").permitAll() // Allow login using oauth2
                        .requestMatchers("/api/**").permitAll() // For testing crud endpoints only
//                        .requestMatchers("/api/**").authenticated() // Secure API endpoints
                )

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Ensure stateless sessions for API

                .oauth2Login(oauth -> oauth.successHandler(oauth2CustomSuccessHandler)) // Add custom Oauth2 success handler

                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // Add custom filter before default authentication filter

                .httpBasic(AbstractHttpConfigurer::disable) // Disable http basic

                .formLogin(AbstractHttpConfigurer::disable) // Disable form login

                .csrf(AbstractHttpConfigurer::disable) // Disable csrf

                .headers(head -> head.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)) // Disable frame options for h2-console but h2 console is still not working lol

                .build();
    }

}
