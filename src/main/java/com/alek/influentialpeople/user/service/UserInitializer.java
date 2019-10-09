package com.alek.influentialpeople.user.service;

import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.persistence.UserRepository;
import com.alek.influentialpeople.user.role.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserInitializer {

    @Autowired
    public UserInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {

        User admin = User.builder().username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles(new HashSet(Arrays.asList(new Role(Role.Roles.ROLE_ADMIN), new Role(Role.Roles.ROLE_USER))))
                .build();

        userRepository.save(admin);
    }
}