package com.alek.influentialpeople.user.service;

public interface UserManager {

    void changePassword(String password);

    void changeEmail(String email);

    void changeRole(String username, String role);

    String resetPassword(String username);
}
