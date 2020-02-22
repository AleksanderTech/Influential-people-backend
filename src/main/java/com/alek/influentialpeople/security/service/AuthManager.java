package com.alek.influentialpeople.security.service;

public interface AuthManager {

    String authenticate(String username, String password);
}
