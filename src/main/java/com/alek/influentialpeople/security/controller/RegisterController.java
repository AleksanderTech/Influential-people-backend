package com.alek.influentialpeople.security.controller;

import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.security.model.UserRegistration;
import com.alek.influentialpeople.security.service.RegisterManager;
import com.alek.influentialpeople.security.service.UserRegistrationConverter;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.model.UserResponse;
import com.alek.influentialpeople.user.service.UserResponseConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@RestController
public class RegisterController {

    private final RegisterManager<User> registerService;

    private TwoWayConverter<UserRegistration, User> regConverter = new UserRegistrationConverter();
    private TwoWayConverter<User, UserResponse> resConverter = new UserResponseConverter();

    public RegisterController(RegisterManager<User> registerService) {
        this.registerService = registerService;
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public ResponseEntity<UserResponse> signUp(@RequestBody UserRegistration userRegistration) {
        User user = regConverter.convert(userRegistration);
        user = registerService.signUp(user);
        return new ResponseEntity(resConverter.convert(user), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public RedirectView confirm(@RequestParam(required = true, name = "token") String token) {
        return new RedirectView(registerService.confirm(token));
    }
}
