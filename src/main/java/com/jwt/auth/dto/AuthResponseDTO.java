package com.jwt.auth.dto;

public record AuthResponseDTO(
   TokenResponseDTO token,
   UserinfoDTO user
) {}
