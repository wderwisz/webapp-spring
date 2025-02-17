package com.example.microservices.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class GatewayConfig implements WebFluxConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**") // Dopuszczamy wszystkie endpointy
//                .allowedOrigins("http://localhost:4200") // Dopuszczamy frontend
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Lista metod HTTP
//                .allowedHeaders("*") // Dopuszczamy wszystkie nagłówki
//                .allowCredentials(true); // Wsparcie dla ciasteczek/autoryzacji
    }
}