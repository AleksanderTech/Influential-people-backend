package com.alek.influentialpeople.user.controller;

import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.common.abstraction.CrudService;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.model.UserAccount;
import com.alek.influentialpeople.user.model.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.alek.influentialpeople.common.ConvertersFactory.ConverterType;
import static com.alek.influentialpeople.common.ConvertersFactory.getConverter;

@RestController
@RequestMapping("/user")
public class UserCrudController {

    private final CrudService<User, String> userService;
    private TwoWayConverter<UserAccount, User> accConverter = getConverter(ConverterType.USER_ACCOUNT_TO_USER);
    private TwoWayConverter<User, UserResponse> resConverter = getConverter(ConverterType.USER_TO_USER_RESPONSE);

    public UserCrudController(CrudService<User, String> userService) {
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
    public ResponseEntity delete(@PathVariable String username) {
        userService.delete(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping(path = "/{username}", method = RequestMethod.GET)
    public ResponseEntity<UserResponse> findOne(@PathVariable String username) {
        User user = userService.findOne(username);
        return new ResponseEntity<>(resConverter.convert(user), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserResponse> create(@RequestBody UserAccount user) {
        return new ResponseEntity<>(resConverter.convert(userService.create(accConverter.convert(user))), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.PATCH)
    public ResponseEntity<UserResponse> update(@RequestBody UserAccount user) {
        return new ResponseEntity<>(resConverter.convert(userService.update(user.getUsername(), accConverter.convert(user))), HttpStatus.OK);
    }
}
