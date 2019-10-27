package com.alek.influentialpeople.user.service;

import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.model.UserAccount;
import com.alek.influentialpeople.user.role.entity.Role;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserAccountConverter extends TwoWayConverter<UserAccount, User> {

    @Override
    public User convert(UserAccount from) {
        return User.builder().username(from.getUsername()).roles(from.getRoles().stream().map(role -> new Role(role)).collect(Collectors.toSet())).email(from.getEmail()).password(from.getPassword()).build();
    }

    @Override
    public UserAccount convertBack(User from) {
        return UserAccount.builder().username(from.getUsername()).roles(from.getRoles().stream().map(role -> role.getName()).collect(Collectors.toSet())).email(from.getEmail()).password(from.getPassword()).build();
    }
}
