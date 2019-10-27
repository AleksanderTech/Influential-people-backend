package com.alek.influentialpeople.user.controller;

import com.alek.influentialpeople.user.model.UserAccount;
import com.alek.influentialpeople.user.model.UserResponse;
import com.alek.influentialpeople.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<UserResponse>> findAll(Pageable pageable) {

        return new ResponseEntity(userService.findAll(pageable), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/{username}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable String username) {

        userService.deleteUser(username, true);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping(path = "/{username}", method = RequestMethod.GET)
    public ResponseEntity<UserResponse> findUser(@PathVariable String username) {

        return new ResponseEntity(userService.findUser(username, true), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserResponse> createUser(@RequestBody UserAccount user) {

        return new ResponseEntity(userService.createUser(user, false), HttpStatus.CREATED);
    }
}
