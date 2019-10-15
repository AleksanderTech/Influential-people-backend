package com.alek.influentialpeople.security.service;

import com.alek.influentialpeople.security.model.UserRegistration;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.persistence.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public void signUp(UserRegistration userRegistration) {
//    userRepository.save(new User().builder().username(userRegistration.getUsername()).password(.))
//    }
//
//    private

}
