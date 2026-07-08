package com.jwt.auth.controller.auth;

import com.jwt.auth.controller.docs.AuthControllerDocs;
import com.jwt.auth.dto.auth.AuthResponseDTO;
import com.jwt.auth.dto.login.LoginRequestDTO;
import com.jwt.auth.dto.register.RegisterRequestDTO;
import com.jwt.auth.dto.register.RegisterResponseDTO;
import com.jwt.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController implements AuthControllerDocs {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(
            @RequestBody @Valid RegisterRequestDTO request
            ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.register(request));
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(
            @RequestBody @Valid LoginRequestDTO request
            ) {
        return ResponseEntity.ok(
                authService.login(request)
        );
    }
}
