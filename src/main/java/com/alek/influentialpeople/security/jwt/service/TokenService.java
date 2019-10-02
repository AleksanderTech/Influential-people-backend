package com.alek.influentialpeople.security.jwt.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;
    public static final long VALIDITY_IN_SECONDS = 86400;

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    public Date getExpirationDate(String token) {
        return getClaims(token).getExpiration();
    }

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) {
        return getExpirationDate(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {

        return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + VALIDITY_IN_SECONDS * 1000))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public boolean validate(String token, UserDetails userDetails) {
        return (getUsername(token).equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
