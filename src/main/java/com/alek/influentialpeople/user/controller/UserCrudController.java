package com.alek.influentialpeople.user.controller;

import com.alek.influentialpeople.common.ImageManager;
import com.alek.influentialpeople.common.ImageType;
import com.alek.influentialpeople.common.Properties;
import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.common.abstraction.CrudService;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.model.UserAccount;
import com.alek.influentialpeople.user.model.UserResponse;
import com.alek.influentialpeople.user.service.UserAccountConverter;
import com.alek.influentialpeople.user.service.UserResponseConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserCrudController {

    private TwoWayConverter<UserAccount, User> accConverter = new UserAccountConverter();
    private TwoWayConverter<User, UserResponse> resConverter = new UserResponseConverter();

    private final Properties properties;
    private final CrudService<User, String> userService;

    public UserCrudController(Properties properties, CrudService<User, String> userService) {
        this.properties = properties;
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<UserResponse>> findAll(Pageable pageable) {
        Page<UserResponse> userResponses = userService.findAll(pageable)
                .map(user -> {
                    UserResponse response = resConverter.convert(user);
                    response.setAvatarImageUrl(ImageManager.createUrl(user.getAvatarImagePath(), properties.getConfig("server.url"), ImageType.USER, user.getUsername()));
                    return response;
                });
        return new ResponseEntity<>(userResponses, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/{username}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable String username) {
        userService.delete(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping(path = "/{username}", method = RequestMethod.GET)
    public ResponseEntity<UserResponse> findOne(@PathVariable String username) {
        User user = userService.findOne(username);
        UserResponse response = resConverter.convert(user);
        response.setAvatarImageUrl(ImageManager.createUrl(user.getAvatarImagePath(), properties.getConfig("server.url"), ImageType.USER, user.getUsername()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserResponse> create(@RequestBody UserAccount user) {
        return new ResponseEntity<>(resConverter.convert(userService.create(accConverter.convert(user))), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/{username}", method = RequestMethod.PATCH)
    public ResponseEntity<UserResponse> update(@PathVariable String username, @RequestBody UserAccount user) {
        return new ResponseEntity<>(resConverter.convert(userService.update(username, accConverter.convert(user))), HttpStatus.OK);
    }
}
