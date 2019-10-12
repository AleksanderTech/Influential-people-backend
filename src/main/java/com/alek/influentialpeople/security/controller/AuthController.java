package com.alek.influentialpeople.security.controller;

import com.alek.influentialpeople.security.model.UserRegistration;
import com.alek.influentialpeople.user.model.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public ResponseEntity<UserResponse> signUp(@RequestBody UserRegistration userRegistration) {



        return new ResponseEntity(null, HttpStatus.CREATED);
    }
}
