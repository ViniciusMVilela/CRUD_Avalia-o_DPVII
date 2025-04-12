package com.example.DP_RPG.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI rpgManagementOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("RPG Management API")
                        .description("API for managing characters and magic items in an RPG game")
                        .version("v1.0.0"));

    }
}