package com.jwt.auth.service;

import com.jwt.auth.domain.User;
import com.jwt.auth.domain.UserRole;
import com.jwt.auth.dto.*;
import com.jwt.auth.exception.ConflictException;
import com.jwt.auth.mapper.UserMapper;
import com.jwt.auth.repository.UserRepository;
import com.jwt.auth.security.JwtTokenProvider;
import com.jwt.auth.security.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.jwt.auth.security.SecurityConstants.PREFIX_BEARER;

@Service
public class AuthService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthService.class);
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            UserMapper userMapper,
            UserRepository userRepository,
            JwtTokenProvider jwtTokenProvider
    ) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Transactional
    public RegisterResponseDTO register(RegisterRequestDTO request) {

        LOG.info("Registering user with username: {}", request.username());

        boolean exists =
                userRepository.existsByEmail(request.email());

        if (exists) {
            throw new ConflictException("Email", request.email());
        }

        String encodedPassword =
                passwordEncoder.encode(request.password());

        User user = userMapper.toEntity(request, encodedPassword);
        user.setPassword(encodedPassword);
        user.setRole(UserRole.ROLE_USER);

        var savedUser =
                userRepository.save(user);

        return userMapper.toRegisterResponse(savedUser);
    }

    @Transactional
    public AuthResponseDTO login(LoginRequestDTO request) {

        LOG.info("Authenticating user with email: {}", request.email());

        UsernamePasswordAuthenticationToken userAndPass =
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                );

        Authentication authentication =
                authenticationManager.authenticate(userAndPass);

        var principal =
                (UserDetailsImpl) authentication.getPrincipal();

        User user =
                principal.getUser();

        String accessToken =
                jwtTokenProvider.generateToken(
                        user.getEmail(),
                        user.getRole().name()
                );

        LOG.info("User '{}' authenticated successfully.", request.email());

        return userMapper.toAuthResponse(
                user,
                accessToken,
                PREFIX_BEARER,
                jwtTokenProvider.getExpirationInSeconds()
        );

    }
}
