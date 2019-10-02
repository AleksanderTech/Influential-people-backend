package com.alek.influentialpeople.security.jwt;

import com.alek.influentialpeople.security.config.SecurityConfig;
import com.alek.influentialpeople.security.jwt.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private UserDetailsService jwtUserDetailsService;
    private TokenService tokenService;

    public JwtAuthorizationFilter(UserDetailsService jwtUserDetailsService, TokenService tokenService) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String username;
        String path = request.getServletPath();
        if (Arrays.asList(SecurityConfig.AUTH_WHITELIST).contains(path)) {
            chain.doFilter(request, response);
            return;
        } else {
            String jwtToken;
            String tokenHeader = request.getHeader("Authorization");
            if (tokenHeader != null && tokenHeader.contains("Bearer")) {
                jwtToken = tokenHeader.replaceFirst("Bearer", "");
            } else {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return;
            }
            username = tokenService.getUsername(jwtToken);
            if (username != null) {
                UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
                if (tokenService.validate(jwtToken, userDetails)) {

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    chain.doFilter(request, response);
                }
            }
        }
    }
}