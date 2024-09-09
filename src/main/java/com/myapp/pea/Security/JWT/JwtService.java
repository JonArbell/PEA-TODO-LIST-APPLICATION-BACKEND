package com.myapp.pea.Security.JWT;

import com.myapp.pea.Models.UserPrincipal;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JwtService {

    private final Logger logger = LoggerFactory.getLogger(JwtService.class);
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    public String generateToken(Authentication authentication){

        var userPrincipal = (UserPrincipal) authentication.getPrincipal();

        var claims = JwtClaimsSet.builder()
                .issuer("https://pea-todo-list-application.onrender.com/")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(60 * 30))
                .id(userPrincipal.getId().toString())
                .subject(authentication.getName())
                .claim("roles",getClaim(authentication))
                .build();

        var parameters = JwtEncoderParameters.from(claims);

        return jwtEncoder.encode(parameters).getTokenValue();
    }

    public String getClaim(Authentication authentication){
        return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(
                " "));
    }

    public String extractUsername(String token) throws JwtException{
        try{
            var username = jwtDecoder.decode(token);
            return username.getSubject();
        }catch (JwtException e){
            logger.error("Failed to extract username from token: {}",e.getMessage());
            return null;
        }
    }

    public boolean isTokenValid(String token) {
        try {
            Jwt decodedJwt = jwtDecoder.decode(token);

            Instant expiration = decodedJwt.getExpiresAt();
            String issuer = decodedJwt.getIssuer().toString();

            return (expiration.isAfter(Instant.now()) && issuer.equals("https://pea-todo-list-application.onrender.com/"));
        } catch (JwtException e) {
            logger.error("Invalid token: {}", e.getMessage());
            return false;
        }
    }

}
