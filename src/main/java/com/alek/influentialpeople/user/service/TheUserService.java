package com.alek.influentialpeople.user.service;

import com.alek.influentialpeople.user.controller.dto.UserResponseDto;
import com.alek.influentialpeople.user.domain.User;
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
    public Page<UserResponseDto> findAll(Pageable pageable) {
        Page<User> page = userRepository.findAll(pageable);
          return new PageImpl<UserResponseDto>(page.getContent().stream().map(user->new UserResponseDto(user)).collect(Collectors.toList()), pageable, page.getTotalElements());
//        return userRepository.findAll(pageable).stream().map(user->new UserResponseDto(user)).collect(Collectors.toList());
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
