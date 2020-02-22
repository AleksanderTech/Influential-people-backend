package com.alek.influentialpeople.user.service;

public interface UserDataHolder<T> {

    T getUser();

    String getUsername();

    boolean isUserAdmin(T user);
}
