package com.UserManagement.UserManagement.Security.Jwt;

import com.UserManagement.UserManagement.Security.UserDetailsImplement;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtility {
    // impoert logger
    private static Logger logger = LoggerFactory.getLogger(JwtUtility.class);

    @Value("${secret_key}")
    private String SECRET_KEY;


    // generate JWT
    public String generateJwtToken(Authentication authentication) {
        UserDetailsImplement userPrincipal = (UserDetailsImplement) authentication.getPrincipal();

        Map<String, Object> claims = new HashMap<>();
        claims.put("subject", userPrincipal.getUsername());
        claims.put("issuedAt", new Date(System.currentTimeMillis()));
        claims.put("expiredAt", new Date(System.currentTimeMillis() + 1000 * 60 * 120));

        return Jwts.builder()
                .claims().add(claims).and()
                .signWith(getSignInkey(), Jwts.SIG.HS256)
                .compact();
    }

    // implementation of the get sign in key for extracting key for jwt generation
    private SecretKey getSignInkey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // extract username from jwt
    public String getUserNameFromJwt(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSignInkey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }
}
