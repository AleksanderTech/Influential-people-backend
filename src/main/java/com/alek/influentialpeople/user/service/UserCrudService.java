package com.alek.influentialpeople.user.service;

import com.alek.influentialpeople.common.abstraction.CrudService;
import com.alek.influentialpeople.exception.ExceptionMessages;
import com.alek.influentialpeople.exception.exceptions.StateConflictException;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.persistence.UserCrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserCrudService implements CrudService<User, String> {

    private final UserCrudRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUserHolder userHolder;

    public UserCrudService(UserCrudRepository userRepository, PasswordEncoder passwordEncoder, CurrentUserHolder userHolder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userHolder = userHolder;
    }

    @Override
    public User findOne(String username) {
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
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User update(String username, User changes) {
       return userRepository.save(changes);
    }

    @Override
    public void delete(String username) {
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

    private boolean isAllowed(String username) {
        User user = userHolder.getUser();
        return user.getUsername().equals(username) || userHolder.isUserAdmin(user);
    }
}
