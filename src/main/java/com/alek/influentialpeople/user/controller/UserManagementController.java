package com.alek.influentialpeople.user.controller;

import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.model.*;
import com.alek.influentialpeople.user.service.UserManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.alek.influentialpeople.common.ConvertersFactory.ConverterType;
import static com.alek.influentialpeople.common.ConvertersFactory.getConverter;

@RestController
@RequestMapping("/user")
public class UserManagementController {

    private final UserManager userService;
    private TwoWayConverter<UserAccount, User> accConverter = getConverter(ConverterType.USER_ACCOUNT_TO_USER);
    private TwoWayConverter<User, UserResponse> resConverter = getConverter(ConverterType.USER_TO_USER_RESPONSE);

    public UserManagementController(UserManager userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/password", method = RequestMethod.PUT)
    public ResponseEntity<Void> changePassword(@RequestBody UserPassword newPassword) {
        userService.changePassword(newPassword.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/email", method = RequestMethod.PUT)
    public ResponseEntity<Void> changeEmail(@RequestBody UserEmail newEmail) {
        userService.changeEmail(newEmail.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/{username}/role", method = RequestMethod.PUT)
    public ResponseEntity<Void> changeRole(@PathVariable String username, @RequestBody UserRole newRole) {
        userService.changeRole(username, newRole.getRole());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/{username}/password", method = RequestMethod.PUT)
    public ResponseEntity<UserPassword> resetPassword(@PathVariable String username) {
        return new ResponseEntity<>(new UserPassword(userService.resetPassword(username)), HttpStatus.OK);
    }
}
