package com.jwt.auth.documentation.annotation;

import com.jwt.auth.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.jwt.auth.documentation.constants.ApiResponseConstants.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses({
        @ApiResponse(
                responseCode = OK,
                description = DESC_OK
        ),
        @ApiResponse(
                responseCode = UNAUTHORIZED,
                description = DESC_UNAUTHORIZED,
                content = @Content(
                        schema = @Schema(implementation = ErrorResponse.class)
                )
        ),
        @ApiResponse(
                responseCode = FORBIDDEN,
                description = DESC_FORBIDDEN,
                content = @Content(
                        schema = @Schema(implementation = ErrorResponse.class)
                )
        )
})
public @interface MeResponses {
}