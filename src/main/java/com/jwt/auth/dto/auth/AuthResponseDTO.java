package com.jwt.auth.dto.auth;

import com.jwt.auth.dto.user.UserinfoDTO;

public record AuthResponseDTO(
   TokenResponseDTO token,
   UserinfoDTO user
) {}
