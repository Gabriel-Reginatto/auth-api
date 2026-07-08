package com.jwt.auth.dto.login;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(

        @Schema(
                description = "User email address",
                example = "gabriel.reginatto@gmail.com"
        )
        @NotBlank(message = "Email is required.")
        @Email(message = "Invalid email address.")
        String email,

        @Schema(
                description = "User password",
                example = "StrongPassword123!"
        )
        @NotBlank(message = "Password is required.")
        String password

) {}