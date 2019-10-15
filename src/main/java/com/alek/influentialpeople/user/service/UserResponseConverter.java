package com.alek.influentialpeople.user.service;

import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.model.UserResponse;
import com.alek.influentialpeople.user.role.entity.Role;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserResponseConverter extends TwoWayConverter<User, UserResponse> {

    @Override
    public UserResponse convert(User from) {
        return UserResponse.builder().username(from.getUsername()).roles(from.getRoles().stream().map(role -> role.getName()).collect(Collectors.toSet())).build();
    }

    @Override
    public User convertBack(UserResponse from) {
        return User.builder().username(from.getUsername()).roles(from.getRoles().stream().map(role -> new Role(Role.Roles.valueOf(role))).collect(Collectors.toSet())).build();
    }
}
