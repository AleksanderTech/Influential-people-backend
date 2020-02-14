package com.alek.influentialpeople.security.service;

import com.alek.influentialpeople.exception.ExceptionMessages;
import com.alek.influentialpeople.exception.exceptions.DisabledUserException;
import com.alek.influentialpeople.exception.exceptions.IncorrectPasswordException;
import com.alek.influentialpeople.security.jwt.JWTService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager manager;
    private final UserDetailsService userDetService;
    private final PasswordEncoder encoder;
    private final JWTService jwtService;

    public AuthService(AuthenticationManager manager, UserDetailsService userDetService, PasswordEncoder encoder, JWTService jwtService) {
        this.manager = manager;
        this.userDetService = userDetService;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }

    public String authenticate(String username, String password) {

        final UserDetails userDetails = userDetService.loadUserByUsername(username);
        validate(userDetails.isEnabled(),userDetails.getPassword(),password);
        manager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return jwtService.generateToken(userDetails);
    }

    private void validate(boolean isEnabled,String userPassword,String providedPassword) {
        if (!isEnabled) {
            throw new DisabledUserException(ExceptionMessages.USER_DISABLED_MESSAGE);
        }
        if (!encoder.matches(providedPassword, userPassword)) {
            throw new IncorrectPasswordException(ExceptionMessages.INCORRECT_PASSWORD_MESSAGE);
        }
    }
}
