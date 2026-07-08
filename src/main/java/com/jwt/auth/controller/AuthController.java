package com.jwt.auth.controller;

import com.jwt.auth.dto.AuthResponseDTO;
import com.jwt.auth.dto.LoginRequestDTO;
import com.jwt.auth.dto.RegisterRequestDTO;
import com.jwt.auth.dto.RegisterResponseDTO;
import com.jwt.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(
            @RequestBody @Valid RegisterRequestDTO request
            ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(
            @RequestBody @Valid LoginRequestDTO request
            ) {
        return ResponseEntity.ok(
                authService.login(request)
        );
    }
}
