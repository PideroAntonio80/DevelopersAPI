package com.sanvalero.quintapracticaaccesodatosmayo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Creado por @ author: Pedro Orós
 * el 18/05/2021
 */

@Configuration
public class DevelopersConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Developers")
                        .description("DEVELOPERS API REST")
                        .contact(new Contact()
                                .name("Pedro Oros")
                                .email("shadycreek@hotmail.com")
                                .url("https://github.com/PideroAntonio80/DevelopersAPI.git"))
                        .version("1.0"));
    }
}
