package com.jwt.auth.controller.docs;

import com.jwt.auth.documentation.annotation.LoginResponses;
import com.jwt.auth.documentation.annotation.RegisterResponses;
import com.jwt.auth.dto.auth.AuthResponseDTO;
import com.jwt.auth.dto.login.LoginRequestDTO;
import com.jwt.auth.dto.register.RegisterRequestDTO;
import com.jwt.auth.dto.register.RegisterResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(
        name = "Authentication",
        description = "Endpoints responsible for user registration and authentication."
)
public interface AuthControllerDocs {

    @Operation(
            summary = "Register a new user",
            description = """
                    Creates a new user account.
                    
                    The email address must be unique and the password will be securely encrypted
                    before being stored in the database.
                    """
    )
    @RegisterResponses
    ResponseEntity<RegisterResponseDTO> register(
            @Valid @RequestBody RegisterRequestDTO request
    );

    @Operation(
            summary = "Authenticate a user",
            description = """
                    Authenticates the user using email and password.
                    
                    If the credentials are valid, the API returns a JWT access token
                    that must be sent in the Authorization header using the Bearer scheme.
                    """
    )
    @LoginResponses
    ResponseEntity<AuthResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO request
    );
}