package com.alek.influentialpeople.user.controller;

import com.alek.influentialpeople.article.service.ArticleService;
import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.model.*;
import com.alek.influentialpeople.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.alek.influentialpeople.common.ConvertersFactory.ConverterType;
import static com.alek.influentialpeople.common.ConvertersFactory.getConverter;

@RestController
@RequestMapping("/user")
public class UserController {

    private TwoWayConverter<UserAccount, User> accConverter = getConverter(ConverterType.USER_ACCOUNT_TO_USER);
    private TwoWayConverter<User, UserResponse> resConverter = getConverter(ConverterType.USER_TO_USER_RESPONSE);
    private UserService userService;
    private ArticleService articleService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<UserResponse>> findAll(Pageable pageable) {

        Page<UserResponse> userResponses = userService.findAll(pageable).map(user -> resConverter.convert(user));
        return new ResponseEntity<>(userResponses, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/{username}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable String username) {

        userService.deleteUser(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping(path = "/{username}", method = RequestMethod.GET)
    public ResponseEntity<UserResponse> findUser(@PathVariable String username) {

        User user = userService.findUser(username);
        return new ResponseEntity<>(resConverter.convert(user), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserResponse> createUser(@RequestBody UserAccount user) {
        return new ResponseEntity<>(resConverter.convert(userService.createUser(accConverter.convert(user))), HttpStatus.CREATED);
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

    @RequestMapping(path = "/{username}/image", method = RequestMethod.PUT)
    public ResponseEntity uploadAvatarImage(@PathVariable(name = "username") String username, @RequestPart(value = "image", required = false) MultipartFile image) {

        userService.storeUserImage(username,image);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/{username}/image", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getAvatarImage(@PathVariable(name = "username") String username) {

        byte[] image = userService.getUserImage(username);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }
}
