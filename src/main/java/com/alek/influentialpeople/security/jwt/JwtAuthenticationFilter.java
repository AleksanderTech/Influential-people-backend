package com.alek.influentialpeople.security.jwt;

import com.alek.influentialpeople.security.SecurityConstants;
import com.alek.influentialpeople.security.jwt.service.TokenService;
import com.alek.influentialpeople.security.model.CurrentUser;
import com.alek.influentialpeople.user.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private TokenService tokenService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            User user = new ObjectMapper()
                    .readValue(req.getInputStream(), User.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword(),
                            new ArrayList())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) {

        String token = tokenService.generateToken(CurrentUser.builder().username(auth.getName()).roles(auth.getAuthorities()).build());

        res.addHeader(SecurityConstants.ACCESS_CONTROL_EXPOSE_HEADERS, SecurityConstants.AUTHORIZATION);
        res.addHeader(SecurityConstants.AUTHORIZATION, SecurityConstants.TOKEN_PREFIX + token);
    }
}
