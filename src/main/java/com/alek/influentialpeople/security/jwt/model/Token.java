package com.alek.influentialpeople.security.jwt.model;

import lombok.Getter;

@Getter
public class Token {

    private String token;

    public Token(String token) {
        this.token = token;
    }
}