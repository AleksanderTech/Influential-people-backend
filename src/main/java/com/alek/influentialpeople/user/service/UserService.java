package com.alek.influentialpeople.user.service;

import com.alek.influentialpeople.user.model.UserAccount;
import com.alek.influentialpeople.user.model.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserResponse> findAll(Pageable pageable);

    UserResponse createUser(UserAccount user, boolean inSecureWay);

    UserResponse findUser(String username,boolean inSecureWay);

    void deleteUser(String username,boolean inSecureWay);
}
