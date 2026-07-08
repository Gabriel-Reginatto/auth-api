package com.jwt.auth.dto;

public record TokenResponseDTO(
        String accessToken,
        String tokenType,
        long expiresIn
) {
}
