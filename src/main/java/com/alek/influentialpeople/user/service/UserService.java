package com.alek.influentialpeople.user.service;

import com.alek.influentialpeople.user.model.UserResponse;
import com.alek.influentialpeople.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserResponse> findAll(Pageable pageable);

    User createUser(User user,boolean inSecureWay);

    User findUser(String username,boolean inSecureWay);

    void deleteUser(String username,boolean inSecureWay);
}
