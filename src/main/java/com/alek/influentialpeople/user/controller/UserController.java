package com.alek.influentialpeople.user.controller;

import com.alek.influentialpeople.user.controller.dto.UserResponse;
import com.alek.influentialpeople.user.domain.User;
import com.alek.influentialpeople.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService theUserService;

    @RequestMapping(method = RequestMethod.GET)
    public Page<UserResponse> findAll(Pageable pageable) {

        return theUserService.findAll(pageable).map(user -> new UserResponse(user));
    }

    @RequestMapping(path = "/{username}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable String username) {

        theUserService.deleteUser(username);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(path = "/{username}", method = RequestMethod.GET)
    public UserResponse findUser(@PathVariable String username) {

        return new UserResponse(theUserService.findUser(username));
    }

    @RequestMapping(method = RequestMethod.POST)
    public User createUser(@RequestBody User user) {

        return theUserService.createUser(user);
    }
}
