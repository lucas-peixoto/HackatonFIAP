package br.com.fiap.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/room/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("http://localhost:8082")
                )
                .route(r -> r.path("/api/services/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("http://localhost:8083")
                )
                .route(r -> r.path("/api/users/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("http://localhost:8084")
                )
                .route(r -> r.path("/api/booking/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("http://localhost:8085")
                )
                .build();
    }
}
