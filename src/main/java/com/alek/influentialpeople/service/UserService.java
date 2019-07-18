package com.alek.influentialpeople.service;

import com.alek.influentialpeople.persistence.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserByName(String name);

    void addUser(User user);

    void updateUser(User user);

    User getUser(long id);

    List<User> getUsersForId(Long id);

    List<User> getUsersPaginated(Long start, Long size);
}
