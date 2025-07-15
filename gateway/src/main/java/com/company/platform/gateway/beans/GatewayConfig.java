package com.company.platform.gateway.beans;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
public class GatewayConfig {

    //Ruta Estaticas
    @Bean
    @Profile("eureka-off")
    public RouteLocator routeLocatorEurekaOff(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(
                        route ->
                                route.path("/msvc-companies/**")
                                        .uri("http://localhost:8080")
                ).route(

                        route -> route.path("/msvc-report-ms/**")
                                .uri("http://localhost:7070")
                )
                .build();
    }

    //Rutas Dinamicas
    @Bean
    @Profile("eureka-on")
    public RouteLocator routeLocatorEurekaOn(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(
                        route -> route.path("/msvc-companies/**")
                                .uri("lb://msvc-companies")
                ).route(
                        route -> route.path("/msvc-report-ms/**")
                                .uri("lb://msvc-report-ms")
                )
                .build();
    }
}
