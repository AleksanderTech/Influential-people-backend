package com.alek.influentialpeople.user.service;

import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.persistence.UserRepository;
import com.alek.influentialpeople.user.role.entity.Role;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserHolder implements UserDataHolder<User> {

    private UserRepository userRepository;

    public CurrentUserHolder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser() {

        return userRepository.findById(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()).get();
    }

    @Override
    public String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }

    @Override
    public boolean isUserAdmin(User user) {
        return user.getRoles().contains(Role.Roles.ROLE_ADMIN.name());
    }
}

