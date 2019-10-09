package com.alek.influentialpeople.user.service;

import com.alek.influentialpeople.user.model.UserResponse;
import com.alek.influentialpeople.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserResponse> findAll(Pageable pageable);

    User createUser(User user);

    User findUser(String username);

    void deleteUser(String username);
}
