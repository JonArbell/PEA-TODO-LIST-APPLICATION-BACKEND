package com.myapp.pea.Security.JWT;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private int expiration;

    public String getJwtHeader(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        logger.debug("Header : ", header);

        if(header != null && header.startsWith("Bearer ")){
            return header.substring(7);
        }
        return null;
    }

    public String generateToken(UserDetails userDetails){
        var username = userDetails.getUsername();
        logger.debug("Username : ", username);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getKey())
                .compact();
    }

    private Key getKey(){
        byte[] keys = Decoders.BASE64.decode(secretKey);
        logger.debug("Keys : ", keys);
        return Keys.hmacShaKeyFor(keys);
    }

    public String extractUsername(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateJwtToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token);
            logger.debug("Try");
            return true;
        }catch (SignatureException e){
            logger.debug("SignatureException : ",e.getMessage());
        }
        catch (ExpiredJwtException e){
            logger.debug("ExpiredJwtException : ",e.getMessage());
        }
        catch (Exception e){
            logger.debug("Exception : ",e.getMessage());
        }
        return false;
    }



}
