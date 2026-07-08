package com.jwt.auth.security;

public final class SecurityConstants {

    private SecurityConstants() {}

    public static final String PREFIX_BEARER = "Bearer ";
    public static final String AUTHORIZATION_HEADER = "Authorization";

    public static final String[] PUBLIC_PATH = {
            "/api/v1/auth/register",
            "/api/v1/auth/login"
    };
}
