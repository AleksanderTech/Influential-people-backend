package com.alek.influentialpeople.user;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserByName(String name);

    void addUser(User user);

    void updateUser(User user);

    List<User> getUsersForId(Long id);

    List<User> getUsersPaginated(Long start, Long size);

    User getUser(String username);
}
