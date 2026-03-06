package com.isobar.isobarbackend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI isobarOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Isobar Band API")
                        .description("API para consulta e filtragem de bandas musicais")
                        .version("v1.0.0"));
    }
}