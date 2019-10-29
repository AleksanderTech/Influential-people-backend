package com.alek.influentialpeople.security.controller;

import com.alek.influentialpeople.common.ConvertersFactory;
import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.security.model.UserRegistration;
import com.alek.influentialpeople.security.service.AuthService;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.model.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import static com.alek.influentialpeople.common.ConvertersFactory.*;

@RestController
public class AuthController {

    private AuthService authService;
    private TwoWayConverter<UserRegistration,User>regConverter=getConverter(ConverterType.USER_REGISTRATION_TO_USER);
    private TwoWayConverter<User,UserResponse>resConverter=getConverter(ConverterType.USER_TO_USER_RESPONSE);

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public ResponseEntity<UserResponse> signUp(@RequestBody UserRegistration userRegistration) {

        User user = regConverter.convert(userRegistration);
        user=authService.signUp(user);
        return new ResponseEntity(resConverter.convert(user), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public RedirectView confirm(@RequestParam(required = true, name = "token") String token) {

        return new RedirectView(authService.confirm(token));
    }
}
