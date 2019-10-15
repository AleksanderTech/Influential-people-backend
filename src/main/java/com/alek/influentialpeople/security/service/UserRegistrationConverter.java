package com.alek.influentialpeople.security.service;

import com.alek.influentialpeople.security.model.UserRegistration;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.role.entity.Role;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationConverter extends TwoWayConverter<UserRegistration, User> {

    @Override
    public User convert(UserRegistration from) {
        return User.builder().username(from.getUsername()).password(from.getPassword()).email(from.getEmail()).roles(Sets.newHashSet(new Role(Role.Roles.ROLE_USER))).build();
    }

    @Override
    public UserRegistration convertBack(User from) {
        return UserRegistration.builder().username(from.getUsername()).password(from.getPassword()).email(from.getEmail()).build();
    }
}
