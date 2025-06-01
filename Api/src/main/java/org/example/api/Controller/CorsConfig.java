package org.example.api.Controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // Cambia según tus rutas
                        .allowedOrigins("http://localhost:8081") // <- puerto del frontend web
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")

                        .allowedHeaders("*");
            }
        };
    }
}
