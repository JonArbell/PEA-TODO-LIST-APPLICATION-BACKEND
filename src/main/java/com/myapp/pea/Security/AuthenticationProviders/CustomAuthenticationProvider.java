package com.myapp.pea.Security.AuthenticationProviders;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        log.info("Authentication : {}",authentication);

        var email = authentication.getName();
        var password = authentication.getCredentials().toString();

        log.info("Email : {}",email);
        log.info("Password : {}",password);

        try{

            var userDetails = userDetailsService.loadUserByUsername(email);

            if(!correctPassword(password, userDetails.getPassword()))
                throw new BadCredentialsException("Wrong password.");

            return new UsernamePasswordAuthenticationToken(userDetails, password, authentication.getAuthorities());

        }catch (UsernameNotFoundException e){
            throw new UsernameNotFoundException("No account found with that email. Please double-check your email address or consider creating a new account.");
        }

    }

    public boolean correctPassword(String inputPassword, String userDetailsPassword){
        return passwordEncoder.matches(inputPassword, userDetailsPassword);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
