package com.example.microservices.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

//	@Bean
//	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//		return builder.routes()
//				.route("professions", r -> r
//						.host("gateway-app:8080")
//						.and()
//						.path("/api/professions", "/api/professions/{name}")
//						.uri("http://elements-app:8081"))
//				.route("characters", r -> r
//						.host("gateway-app:8080")
//						.and()
//						.path("/api/characters", "/api/characters/**", "/api/professions/{name}/characters/**", "/api/professions/{name}/characters/")
//						.uri("http://category-app:8082"))
//				.route("professions2", r -> r
//						.host("localhost:8080")
//						.and()
//						.path("/api/professions", "/api/professions/{name}")
//						.uri("http://localhost:8081"))
//				.route("characters2", r -> r
//						.host("localhost:8080")
//						.and()
//						.path("/api/characters", "/api/characters/**", "/api/professions/{name}/characters/**", "/api/professions/{name}/characters/")
//						.uri("http://localhost:8082"))
//				.build();
//	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("professions", r -> r
						.path("/api/professions", "/api/professions/{name}")
						.uri("http://category-app:8081"))
				.route("characters", r -> r
						.path("/api/characters", "/api/characters/**", "/api/professions/{name}/characters/**", "/api/professions/{name}/characters/")
						.uri("http://elements-app:8082"))
				.build();
	}

	@Bean
	public CorsWebFilter corsWebFilter() {

		final CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Collections.singletonList("*"));
		corsConfig.setMaxAge(3600L);
		corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT", "OPTIONS"));
		corsConfig.addAllowedHeader("*");

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);

		return new CorsWebFilter(source);
	}


}