package com.example.easynotes.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger Configuration
 */

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("Easy Notes API Documentation")
                .version("v1.0.0")
                .description("Easy Notes API 명세서 입니다.");
        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
