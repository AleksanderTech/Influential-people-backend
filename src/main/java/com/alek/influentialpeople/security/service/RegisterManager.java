package com.alek.influentialpeople.security.service;

public interface RegisterManager<T> {

    T signUp(T user);

    String confirm(String value);
}
