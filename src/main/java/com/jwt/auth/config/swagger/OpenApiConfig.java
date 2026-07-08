package com.jwt.auth.config.swagger;

import com.jwt.auth.documentation.constants.OpenApiConstants;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private static final String SECURITY_SCHEME_NAME = "Bearer Authentication";

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(apiInfo())

                .addSecurityItem(
                        new SecurityRequirement()
                                .addList(SECURITY_SCHEME_NAME)
                )

                .schemaRequirement(
                        SECURITY_SCHEME_NAME,
                        securityScheme()
                );

    }

    private Info apiInfo() {
        return new Info()
                .title("Auth API")
                .description("""
                        REST API for user authentication and authorization
                        built with Spring Boot, Spring Security and JWT.
                        """)
                .version(OpenApiConstants.API_VERSION)
                .contact(
                        new Contact()
                                .name("Gabriel Reginatto")
                                .email("reginattogb@gmail.com")
                                .url("https://github.com/Gabriel-Reginatto")
                )
                .license(
                        new License()
                                .name("MIT")
                                .url("https://opensource.org/licenses/MIT")
                );
    }

    private SecurityScheme securityScheme() {

        return new SecurityScheme()

                .name(SECURITY_SCHEME_NAME)

                .type(SecurityScheme.Type.HTTP)

                .scheme(OpenApiConstants.BEARER_PREFIX_TO_SWAGGER)

                .bearerFormat(OpenApiConstants.BEARER_FORMAT);
    }
}
