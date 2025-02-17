package com.example.microservices.character_management_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**") // Dopuszczamy wszystkie endpointy
//                .allowedOrigins("http://localhost:4200") // Dopuszczamy frontend
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Lista metod HTTP
//                .allowedHeaders("*") // Dopuszczamy wszystkie nagłówki
//                .allowCredentials(true); // Wsparcie dla ciasteczek/autoryzacji
    }
}