package com.alek.influentialpeople.user.service;

public interface UserDataHolder<T> {

    T getUser();

    boolean isUserAdmin(T user);

}
