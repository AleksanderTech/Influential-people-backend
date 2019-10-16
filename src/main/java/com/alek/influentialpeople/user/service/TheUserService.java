package com.alek.influentialpeople.user.service;

import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.model.UserResponse;
import com.alek.influentialpeople.user.persistence.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class TheUserService implements UserService {

    private UserRepository userRepository;
    private TwoWayConverter<User, UserResponse> converter;
    private PasswordEncoder passwordEncoder;

    public TheUserService(UserRepository userRepository, TwoWayConverter<User, UserResponse> converter, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.converter = converter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Page<UserResponse> findAll(Pageable pageable) {
        Page<User> page = userRepository.findAll(pageable);
        return new PageImpl(page.getContent().stream().map(user -> converter.convert(user)).collect(Collectors.toList()), pageable, page.getTotalElements());
    }

    @Override
    public UserResponse findUser(String name, boolean inSecureWay) {
        return converter.convert(userRepository.findById(name).get());
    }

    @Override
    public void deleteUser(String username, boolean inSecureWay) {
        userRepository.deleteById(username);
    }

    @Override
    public UserResponse createUser(User user, boolean inSecureWay) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return converter.convert(userRepository.save(user));
    }
}
