package com.myapp.pea.Security;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        return http.authorizeHttpRequests(auth -> auth
                                .requestMatchers( "/pea/home/**",
                                        "/pea/todays-tasks/**",
                                        "/pea/all-tasks/**",
                                        "/pea/completed-tasks/**",
                                        "/pea/overdue-tasks/**",
                                        "/about-us",
                                        "/contact-us",
                                        "/settings",
                                        "/pea/todo/**",
                                        "/pea/list/**")
                                .authenticated()
                                .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/pea/login")
                        .defaultSuccessUrl("/pea/home", true)
                        .failureUrl("/pea/login?error=true")
                        .permitAll()
                )
                .logout(log -> log
                        .logoutUrl("/pea/logout")
                        .logoutSuccessUrl("/pea")
                )
                .headers( head -> head
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                )
//                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

}
