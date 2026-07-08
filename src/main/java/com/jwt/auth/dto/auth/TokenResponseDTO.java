package com.jwt.auth.dto.auth;

public record TokenResponseDTO(
        String accessToken,
        String tokenType,
        long expiresIn
) {
}
