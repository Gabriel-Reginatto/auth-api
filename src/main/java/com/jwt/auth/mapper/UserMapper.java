package com.jwt.auth.mapper;

import com.jwt.auth.domain.User;
import com.jwt.auth.dto.*;
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