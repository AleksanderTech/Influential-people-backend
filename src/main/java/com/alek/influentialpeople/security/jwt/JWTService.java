package com.alek.influentialpeople.security.jwt;

import com.alek.influentialpeople.security.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JWTService {

    public static final String AUTHORITIES_CLAIM = "authorities";

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    public Date getExpirationDate(String token) {
        return getClaims(token).getExpiration();
    }

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) {
        return getExpirationDate(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder().claim(AUTHORITIES_CLAIM, userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())).setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.JWT_VALIDITY))
                .signWith(SignatureAlgorithm.HS256, SecurityConstants.SECRET).compact();
    }

    public boolean validate(String token, UserDetails userDetails) {
        return (getUsername(token).equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public List<String> getAuthorities(String token) {
        return getClaims(token).get(AUTHORITIES_CLAIM, List.class);
    }
}
