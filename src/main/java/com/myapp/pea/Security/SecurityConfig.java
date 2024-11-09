package com.myapp.pea.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.pea.Security.JWT.CustomJwtAuthFilter.MyCustomJwtAuthFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import java.util.HashMap;
import java.util.Map;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final MyCustomJwtAuthFilter myCustomJwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .authorizeHttpRequests(auth -> auth /*
                    This code configures all private API requests to require authentication.
                */
                        .requestMatchers(apiRequestMatcher()).authenticated()
                        .anyRequest().permitAll()
                )

                .csrf(cs -> cs.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())) /*
                   This CSRF configuration enables CSRF protection, stores the CSRF token in a cookie,
                   and allows it to be read by JavaScript.
                */

                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> { /*
                   This code configures exception handling for unauthorized requests across all authenticated APIs,
                   responding with a JSON-formatted message.
                */
                    httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint((request, response,
                                                                                      authException) -> {

                        Map<String,String> error = new HashMap<>();
                        error.put("status",Integer.toString(HttpStatus.UNAUTHORIZED.value()));
                        error.put("error","Unauthorized");
                        error.put("message","You need to log in to access this resource.");

                        ObjectMapper mapper = new ObjectMapper();
                        String jsonResponse = mapper.writeValueAsString(error);

                       response.setStatus(HttpStatus.UNAUTHORIZED.value());
                       response.setContentType("application/json");
                       response.getOutputStream().println(jsonResponse);
                    });
                })

                .logout(AbstractHttpConfigurer::disable) // disable logout of spring security

                .addFilterBefore(myCustomJwtAuthFilter, UsernamePasswordAuthenticationFilter.class) /* Custom filter
                first before default UsernamePasswordAuthenticationFilter */

                .oauth2ResourceServer(oath -> oath
                        .jwt(Customizer.withDefaults()) /* Configures the application as an OAuth 2.0 resource
                            server, using JWTs for access tokens. Incoming requests must include a valid JWT in the
                            Authorization header to access protected resources.
                        */
                )
                .authenticationProvider(authenticationProvider()) /* Set authenticationProvider to
                DaoAuthenticationProvider bean */

                .build();
    }

    private RequestMatcher apiRequestMatcher() {
        return new AntPathRequestMatcher("/api/authenticated/**"); // Add Class for all api pattern
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){ // This Bean is for customize the Authentication Provider
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception { /* This bean configures an AuthenticationManager for the authentication REST controller. */
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }

}
