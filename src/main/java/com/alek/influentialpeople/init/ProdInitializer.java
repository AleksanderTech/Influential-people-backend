package com.alek.influentialpeople.init;

import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.persistence.UserRepository;
import com.alek.influentialpeople.user.role.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
@Profile(value = {"prod"})
public class ProdInitializer {

    @Autowired
    public ProdInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {

        User admin = User.builder().username("admin")
                .password(passwordEncoder.encode("admin"))
                .email("email@email.com")
                .enabled(true)
                .roles(new HashSet(Arrays.asList(new Role(Role.Roles.ROLE_ADMIN), new Role(Role.Roles.ROLE_USER))))
                .build();
        if (!userRepository.existsById("username")) {
            userRepository.save(admin);
        }
    }
}