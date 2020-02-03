package com.alek.influentialpeople.user.service;

import com.alek.influentialpeople.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    Page<User> findAll(Pageable pageable);

    User createUser(User user);

    User saveUser(User user);

    User findUser(String username);

    void deleteUser(String username);

    void changePassword(String password);

    void changeEmail(String email);

    void changeRole(String username, String role);

    String resetPassword(String username);

    byte[] getUserImage();

    String storeUserImage(MultipartFile image);
}
