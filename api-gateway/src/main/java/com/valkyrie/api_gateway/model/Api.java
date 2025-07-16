package com.valkyrie.api_gateway.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@Configuration
public class Api {
    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return route("question-post").POST("/question/**", http())
                .before(uri("http://localhost:8081")).build().and(
                        route("question-get").GET("/question/**", http())
                                .before(uri("http://localhost:8081")).build()
                ).and(
                        route("question-delete").DELETE("/question/**", http())
                                .before(uri("http://localhost:8081")).build()
                ).and(
                        route("quiz-post").POST("/quiz/**", http())
                                .before(uri("http://localhost:8082")).build()
                ).and(
                        route("quiz-get").GET("/quiz/**", http())
                                .before(uri("http://localhost:8082")).build()
                ).and(
                        route("user").POST("/user/**", http())
                                .before(uri("http://localhost:8083")).build()
                );
    }
}
