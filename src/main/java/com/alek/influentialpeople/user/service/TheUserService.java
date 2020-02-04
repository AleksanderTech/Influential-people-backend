package com.alek.influentialpeople.user.service;

import com.alek.influentialpeople.common.ImageService;
import com.alek.influentialpeople.exception.ExceptionMessages;
import com.alek.influentialpeople.exception.exceptions.StateConflictException;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.persistence.UserRepository;
import com.alek.influentialpeople.user.role.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.util.Random;
import java.util.Set;

@Service
public class TheUserService implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private CurrentUserHolder userHolder;
    private ImageService imageService;

    private static final int FIXED_GENERATED_PASSWORD_LENGTH = 10;

    public TheUserService(UserRepository userRepository, PasswordEncoder passwordEncoder, CurrentUserHolder userHolder, ImageService imageService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userHolder = userHolder;
        this.imageService = imageService;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAllUsers(pageable);
    }

    @Override
    public User findUser(String username) {
        if (isAllowed(username)) {

            User user = userRepository.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException(ExceptionMessages.NOT_FOUND_USER_MESSAGE);
            }
            return user;
        } else {
            throw new AccessDeniedException(ExceptionMessages.ACCESS_DENIED_MESSAGE);
        }
    }

    @Override
    public void deleteUser(String username) {
        User user = userRepository.findById(username).orElse(null);
        if (userHolder.getUser().getUsername().equals(username)) {
            if (user.getUsername().equals(username)) {
                throw new StateConflictException(ExceptionMessages.STATE_CONFLICT_MESSAGE);
            }
            if (user == null) {
                throw new UsernameNotFoundException(ExceptionMessages.NOT_FOUND_USER_MESSAGE);
            }
        }
        userRepository.deleteById(username);
    }

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
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

    @Override
    public byte[] getUserImage(String username) {
        String path = userRepository.findAvatarPath(username);
        if (path == null || !(new File(path).exists())) {
            throw new EntityNotFoundException(ExceptionMessages.NOT_FOUND_IMAGE_MESSAGE);
        }
        File directory = new File(path);
        byte[] image = imageService.getImage(path);
        return image;
    }

    @Override
    public String storeUserImage(String name, MultipartFile image) {
        if (!name.equals(userHolder.getUsername())) {
            throw new AccessDeniedException(ExceptionMessages.ACCESS_DENIED_MESSAGE);
        }
        boolean exists = userRepository.existsById(name);
        if (!exists) {
            throw new EntityNotFoundException(ExceptionMessages.NOT_FOUND_HERO_MESSAGE);
        }
        String path = userRepository.findAvatarPath(name);
        if (path == null || !new File(path).exists()) {
            path = imageService.createAvatarPath(ImageService.StorageOf.USER, name);
            userRepository.updateImagePath(imageService.appendImageName(name, path), name);
            imageService.storeImage(ImageService.StorageOf.USER, name, image);
        } else {
            imageService.storeImage(path, name, image);
        }
        return imageService.createAvatarUrl(ImageService.StorageOf.USER, name);
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
