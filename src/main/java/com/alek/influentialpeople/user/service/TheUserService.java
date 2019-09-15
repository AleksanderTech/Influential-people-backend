package com.alek.influentialpeople.user.service;

import com.alek.influentialpeople.user.domain.User;
import com.alek.influentialpeople.user.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TheUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findUser(String name) {
        return userRepository.findById(name).get();
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
}
