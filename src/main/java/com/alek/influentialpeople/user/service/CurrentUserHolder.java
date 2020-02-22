package com.alek.influentialpeople.user.service;

import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.persistence.UserCrudRepository;
import com.alek.influentialpeople.user.role.entity.Role;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserHolder implements UserDataHolder<User> {

    private UserCrudRepository userRepository;

    public CurrentUserHolder(UserCrudRepository userRepository) {
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
        Role adminRole = user.getRoles().stream()
          .filter(role -> role.getName().equals(Role.Roles.ROLE_ADMIN.name()))
          .findAny()
          .orElse(null);
        return adminRole != null;
    }
}

