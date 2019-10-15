package com.alek.influentialpeople.user.controller;

import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.model.UserResponse;
import com.alek.influentialpeople.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService theUserService;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public Page<UserResponse> findAll(Pageable pageable) {

        return theUserService.findAll(pageable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/{username}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable String username) {

        theUserService.deleteUser(username, true);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping(path = "/{username}", method = RequestMethod.GET)
    public UserResponse findUser(@PathVariable String username) {

        return theUserService.findUser(username, true);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public UserResponse createUser(@RequestBody User user) {

        return theUserService.createUser(user, true);
    }
}
