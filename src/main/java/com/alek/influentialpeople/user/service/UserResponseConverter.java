package com.alek.influentialpeople.user.service;

import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.model.UserResponse;
import com.alek.influentialpeople.user.role.entity.Role;

import java.util.stream.Collectors;


public class UserResponseConverter extends TwoWayConverter<User, UserResponse> {

    @Override
    public UserResponse convert(User from) {
        return UserResponse.builder().username(from.getUsername()).email(from.getEmail()).avatarImageUrl(from.buildAndSetAvatarUrl()).roles(from.getRoles().stream().map(role -> role.getName()).collect(Collectors.toSet())).build();
    }

    @Override
    public User convertBack(UserResponse from) {
        return User.builder().username(from.getUsername()).email(from.getEmail()).roles(from.getRoles().stream().map(role -> new Role(Role.Roles.valueOf(role))).collect(Collectors.toSet())).build();
    }
}
