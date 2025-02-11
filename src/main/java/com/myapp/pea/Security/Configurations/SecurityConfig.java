package com.myapp.pea.Security.Configurations;

import com.myapp.pea.Security.JWT.JwtAuthenticationFilter;
import com.myapp.pea.Security.Oauth2.Oauth2CustomSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig{

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final Oauth2CustomSuccessHandler oauth2CustomSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        var publicEndpoints = new OrRequestMatcher(
                new AntPathRequestMatcher("/api/authentication/**"),
                new AntPathRequestMatcher("/oauth2/**"),
                new AntPathRequestMatcher("/oauth2/authorization/**"),
                new AntPathRequestMatcher("/h2-console/**")
        );

        return http.authorizeHttpRequests(auth -> auth
                        .requestMatchers(publicEndpoints).permitAll() // Public endpoints
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Ensure stateless sessions for API

                .oauth2Login(oauth -> oauth.successHandler(oauth2CustomSuccessHandler)) // Add custom Oauth2 success handler

                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // Add custom filter before default authentication filter

                .httpBasic(AbstractHttpConfigurer::disable) // Disable http basic

                .formLogin(AbstractHttpConfigurer::disable) // Disable form login

                .csrf(cs -> cs.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .ignoringRequestMatchers(publicEndpoints))

//                .csrf(AbstractHttpConfigurer::disable) // Disable csrf

                .headers(head -> head.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)) // Disable frame options for h2-console but h2 console
                // is
                // still not working lol

                .oauth2ResourceServer(resourceServer -> resourceServer
                        .jwt(Customizer.withDefaults())
                )
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
