package com.jwt.auth.dto;

import java.util.UUID;

public record UserinfoDTO(
        UUID id,
        String username,
        String email,
        String role
) {}
