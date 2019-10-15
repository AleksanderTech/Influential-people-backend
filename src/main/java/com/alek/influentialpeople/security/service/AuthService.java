package com.alek.influentialpeople.security.service;

import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.security.model.UserRegistration;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private UserService userService;
    private TwoWayConverter<UserRegistration, User> converter;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public void signUp(UserRegistration userRegistration) {
        userService.createUser(converter.convert(userRegistration), true);
    }
}
