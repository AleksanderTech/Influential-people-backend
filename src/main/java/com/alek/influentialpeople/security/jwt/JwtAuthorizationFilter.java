package com.alek.influentialpeople.security.jwt;

import com.alek.influentialpeople.security.jwt.service.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

import static com.alek.influentialpeople.security.SecurityConstants.AUTHORIZATION;
import static com.alek.influentialpeople.security.SecurityConstants.TOKEN_PREFIX;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private UserDetailsService jwtUserDetailsService;
    private TokenService tokenService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserDetailsService jwtUserDetailsService, TokenService tokenService) {
        super(authenticationManager);
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {


        String header = request.getHeader(AUTHORIZATION);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION);
        if (token != null) {
            token = token.replaceFirst(TOKEN_PREFIX, "");

            String username = tokenService.getUsername(token);
            if (username != null && tokenService.validate(token, jwtUserDetailsService.loadUserByUsername(username))) {
                return new UsernamePasswordAuthenticationToken(username, null, tokenService.getAuthorities(token).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
            }
            return null;
        }
        return null;
    }

}