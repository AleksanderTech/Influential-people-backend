package com.alek.influentialpeople.user.service;

import com.alek.influentialpeople.common.ImageManager;
import com.alek.influentialpeople.exception.ExceptionMessages;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.persistence.UserCrudRepository;
import com.alek.influentialpeople.user.role.entity.Role;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Random;
import java.util.Set;

@Service
public class UserManagerService implements UserManager {

    private final UserCrudRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUserHolder userHolder;

    private static final int FIXED_GENERATED_PASSWORD_LENGTH = 10;

    public UserManagerService(UserCrudRepository userRepository, PasswordEncoder passwordEncoder, CurrentUserHolder userHolder, ImageManager imageManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userHolder = userHolder;
    }

    @Override
    public void changePassword(String newPassword) {
        User user = userHolder.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public void changeEmail(String email) {
        User user = userHolder.getUser();
        user.setEmail(email);
        userRepository.save(user);
    }

    @Override
    public void changeRole(String login, String newRole) {
        User user = checkIfExist(login);
        if (isAllowed(user.getUsername())) {
            Role role = new Role(Role.Roles.valueOf(newRole));
            Set<Role> roles = user.getRoles();
            roles.add(role);
            user.setRoles(roles);
            userRepository.save(user);
            return;
        }
        throw new AccessDeniedException(ExceptionMessages.ACCESS_DENIED_MESSAGE);
    }

    @Override
    public String resetPassword(String login) {
        User user = checkIfExist(login);
        if (userHolder.isUserAdmin(user)) {
            String generatedPassword = generateNewPassword();
            user.setPassword(passwordEncoder.encode(generatedPassword));
            userRepository.save(user);
            return generatedPassword;
        }
        throw new AccessDeniedException(ExceptionMessages.ACCESS_DENIED_MESSAGE);
    }

    private User checkIfExist(String username) {
        return userRepository.findById(username).orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.NOT_FOUND_USER_MESSAGE)
        );
    }

    private boolean isAllowed(String username) {
        User user = userHolder.getUser();
        return user.getUsername().equals(username) || userHolder.isUserAdmin(user);
    }

    private static String generateNewPassword() {
        String alphanumericCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder("");
        Random random = new Random();
        for (int i = 0; i < FIXED_GENERATED_PASSWORD_LENGTH; i++) {
            builder.append(alphanumericCharacters.charAt(random.nextInt(alphanumericCharacters.length())));
        }
        return builder.toString();
    }
}
