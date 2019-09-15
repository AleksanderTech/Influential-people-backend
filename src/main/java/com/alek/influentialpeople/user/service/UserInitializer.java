package com.alek.influentialpeople.user.service;

import com.alek.influentialpeople.user.domain.User;
import com.alek.influentialpeople.user.persistence.UserRepository;
import com.alek.influentialpeople.user.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInitializer {

    @Autowired
    public UserInitializer(UserRepository repository, PasswordEncoder passwordEncoder) {
        User admin = User.builder().username("admin")
                .password(passwordEncoder.encode("admin"))
                .role(Role.Roles.ADMIN)
                .build();

        repository.save(admin);
    }
}