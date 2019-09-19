package com.alek.influentialpeople.user.service;

import com.alek.influentialpeople.user.domain.User;
import com.alek.influentialpeople.user.persistence.UserRepository;
import com.alek.influentialpeople.user.role.domain.Role;
import com.alek.influentialpeople.user.role.domain.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserInitializer {

    @Autowired
    public UserInitializer(RoleRepo repo, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        Set<Role> roles = new HashSet<>();
        Role adminRole = new Role("ROLE_ADMIN");
        roles.add(adminRole);
        repo.save(adminRole);

        User admin = User.builder().username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles(new HashSet(Arrays.asList(new Role("ADMIN"))))
                .build();

        userRepository.save(admin);
    }
}