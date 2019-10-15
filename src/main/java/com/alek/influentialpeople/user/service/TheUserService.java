package com.alek.influentialpeople.user.service;

import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.model.UserResponse;
import com.alek.influentialpeople.user.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class TheUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<UserResponse> findAll(Pageable pageable) {
        Page<User> page = userRepository.findAll(pageable);
        return new PageImpl(page.getContent().stream().map(user -> new UserResponse(user)).collect(Collectors.toList()), pageable, page.getTotalElements());
    }

    @Override
    public User findUser(String name, boolean inSecureWay) {
        return userRepository.findById(name).get();
    }

    @Override
    public void deleteUser(String username, boolean inSecureWay) {
        userRepository.deleteById(username);
    }

    @Override
    public User createUser(User user, boolean inSecureWay) {
        return userRepository.save(user);
    }
}
