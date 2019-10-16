package com.alek.influentialpeople.security.controller;

import com.alek.influentialpeople.security.model.UserRegistration;
import com.alek.influentialpeople.security.service.AuthService;
import com.alek.influentialpeople.user.model.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public ResponseEntity<UserResponse> signUp(@RequestBody UserRegistration userRegistration) {

        return new ResponseEntity(authService.signUp(userRegistration), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public RedirectView confirm(@RequestParam(required = true, name = "token") String token) {

        System.out.println(token);
        return new RedirectView("http://localhost:4200");
    }
}
