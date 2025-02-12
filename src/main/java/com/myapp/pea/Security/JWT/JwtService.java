package com.myapp.pea.Security.JWT;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class JwtService {

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    public String generateToken(Authentication authentication) {

        var email = "";

        Collection<? extends GrantedAuthority> authorities = new ArrayList<>();

        var principal = authentication.getPrincipal();

        log.info("True or false : {}",principal instanceof UserDetails);

        if(principal instanceof OAuth2User oAuth2User){
            email = oAuth2User.getAttribute("email");
            authorities = oAuth2User.getAuthorities();
        }else if(principal instanceof UserDetails userDetails){
            email = userDetails.getUsername();
            authorities = userDetails.getAuthorities();
        }

        var claims = JwtClaimsSet.builder()
                .issuer("https://pea-todo-list-application.netlify.app")
                .subject(email)
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(3600))
                .claim("scope",getAuthorities(authorities))
                .build();
        var parameter = JwtEncoderParameters.from(claims);
        return jwtEncoder.encode(parameter).getTokenValue();
    }

    public String extractEmail(String token){
        return jwtDecoder.decode(token)
                .getSubject();
    }

    public boolean validateToken(String token, String email) {
        try {
            var claims = jwtDecoder.decode(token);

            // 🔹 Check if email from token matches
            if (!claims.getSubject().equals(email)) {
                return false;
            }

            // 🔹 Check if token is expired
            return !claims.getExpiresAt().isBefore(Instant.now());

        } catch (JwtException | IllegalArgumentException e) {
            log.error("Invalid JWT Token: {}", e.getMessage());
            return false;
        }
    }

    private String getAuthorities(Collection<? extends GrantedAuthority> authorities){
        return authorities.stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(", "));
    }

}
