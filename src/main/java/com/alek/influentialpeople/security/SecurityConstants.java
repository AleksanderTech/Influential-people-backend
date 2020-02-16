package com.alek.influentialpeople.security;

public class SecurityConstants {

    public static final String SECRET = "secret";
    public static final long JWT_VALIDITY = 864_000_000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String AUTHORIZATION = "Authorization";
    public static final String ACCESS_CONTROL_EXPOSE_HEADERS = "Access-Control-Expose-Headers";
    public static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/webjars/**",
            "/v2/api-docs",
            "/sign-up",
            "/login",
            "/confirm",
            "/h2-console/**",
            "/hero/{\\d+}/image",
            "/user/{\\d+}/image",
            "/category/{\\d+}/image",
    };
}