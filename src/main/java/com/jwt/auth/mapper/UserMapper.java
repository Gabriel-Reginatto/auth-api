package com.jwt.auth.mapper;

import com.jwt.auth.domain.User;
import com.jwt.auth.dto.auth.AuthResponseDTO;
import com.jwt.auth.dto.auth.TokenResponseDTO;
import com.jwt.auth.dto.register.RegisterRequestDTO;
import com.jwt.auth.dto.register.RegisterResponseDTO;
import com.jwt.auth.dto.user.UserinfoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(RegisterRequestDTO request, String encodedPassword);

    RegisterResponseDTO toRegisterResponse(User user);

    UserinfoDTO toUserInfo(User user);

    default AuthResponseDTO toAuthResponse(
            User user,
            String accessToken,
            String tokenType,
            long expiresIn
    ) {

        TokenResponseDTO token = new TokenResponseDTO(
                accessToken,
                tokenType,
                expiresIn
        );

        UserinfoDTO userInfo = toUserInfo(user);

        return new AuthResponseDTO(token, userInfo);
    }
}