package com.nutritonix.api_gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;


@Component
public class JwtUtil {


    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";


    public void validateToken(final String token) {
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
            System.out.println("Token validated! Claims: " + claims);
        } catch (ExpiredJwtException e) {
            System.out.println("JWT Token Expired: " + e.getMessage());
            throw new RuntimeException("JWT Token Expired");
        } catch (Exception e) {
            System.out.println("Invalid JWT Token: " + e.getMessage());
            throw new RuntimeException("Invalid JWT Token");
        }
    }




    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}