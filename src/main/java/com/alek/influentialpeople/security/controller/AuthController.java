package com.alek.influentialpeople.security.controller;

import com.alek.influentialpeople.security.jwt.service.TokenService;
import com.alek.influentialpeople.security.jwt.model.AuthenticationRequest;
import com.alek.influentialpeople.security.jwt.model.Token;
import com.alek.influentialpeople.security.model.UserRegistration;
import com.alek.influentialpeople.user.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private UserDetailsService userDetailsService;
    private AuthenticationManager authenticationManager;
    private TokenService tokenService;

    @Autowired
    public AuthController(UserDetailsService userDetailsService, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Token> authenticate(@RequestBody AuthenticationRequest authentication) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authentication.getUsername(), authentication.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getUsername());
        String token = tokenService.generateToken(userDetails);

        return new ResponseEntity(new Token(token), HttpStatus.OK);
    }


    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public ResponseEntity<UserResponse> signUp(@RequestBody UserRegistration userRegistration) {

        return new ResponseEntity(null, HttpStatus.CREATED);
    }
}
