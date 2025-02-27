package com.myapp.pea.Security.Configurations;

import com.myapp.pea.Security.AuthenticationProviders.CustomAuthenticationProvider;
import com.myapp.pea.Security.JWT.JwtAuthenticationFilter;
import com.myapp.pea.Security.SuccessHandlers.CustomOauth2SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig{

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final CustomAuthenticationProvider customAuthenticationProvider;

    private final CustomOauth2SuccessHandler customOauth2SuccessHandler;

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

                .cors(Customizer.withDefaults())

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Ensure stateless sessions for API

                .oauth2Login(oauth2 -> oauth2
                        .successHandler(customOauth2SuccessHandler) // Add custom Oauth2 success handler
                )

                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // Add custom filter before default authentication filter

                .httpBasic(AbstractHttpConfigurer::disable) // Disable http basic

                .formLogin(AbstractHttpConfigurer::disable) // Disable form login

                .csrf(AbstractHttpConfigurer::disable) // Disable csrf

                .headers(head -> head.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)) // Same origin
                // frame options for enable h2-console in stateless mode

                .oauth2ResourceServer(resourceServer -> resourceServer
                        .jwt(Customizer.withDefaults())
                )

                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return new ProviderManager(List.of(
                customAuthenticationProvider
        ));
    }

}
