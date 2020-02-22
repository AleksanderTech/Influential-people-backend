package com.alek.influentialpeople.security.controller;

import com.alek.influentialpeople.security.SecurityConstants;
import com.alek.influentialpeople.security.jwt.JWTService;
import com.alek.influentialpeople.security.model.AuthRequest;
import com.alek.influentialpeople.security.model.AuthResponse;
import com.alek.influentialpeople.security.service.AuthManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthManager authService;
    private JWTService jwtService;

    public AuthController(AuthManager authService, JWTService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest authRequest) {
        final String jwt = authService.authenticate(authRequest.getUsername(),authRequest.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).header(SecurityConstants.AUTHORIZATION,SecurityConstants.TOKEN_PREFIX+jwt).body(new AuthResponse(jwt));
    }
}
