package com.jwt.auth.dto;

import java.time.Instant;
import java.util.UUID;

public record RegisterResponseDTO(
        UUID id,
        String username,
        String email,
        String role,
        Instant createdAt
) {
}
