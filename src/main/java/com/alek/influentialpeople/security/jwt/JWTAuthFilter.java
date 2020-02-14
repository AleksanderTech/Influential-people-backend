package com.alek.influentialpeople.security.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

import static com.alek.influentialpeople.security.SecurityConstants.AUTHORIZATION;
import static com.alek.influentialpeople.security.SecurityConstants.TOKEN_PREFIX;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    private UserDetailsService userDetService;
    private JWTService JWTService;

    public JWTAuthFilter(UserDetailsService userDetService, JWTService JWTService) {
        this.userDetService = userDetService;
        this.JWTService = JWTService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String header = request.getHeader(AUTHORIZATION);
        String username = null;
        String jwt = null;
        if (header != null && header.startsWith(TOKEN_PREFIX)) {
            jwt = header.replaceFirst(TOKEN_PREFIX, "");
            username = JWTService.getUsername(jwt);
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetService.loadUserByUsername(username);
            if (JWTService.validate(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(username,  null, JWTService.getAuthorities(jwt).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
                upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(upat);
            }
        }
        chain.doFilter(request, response);
    }
}