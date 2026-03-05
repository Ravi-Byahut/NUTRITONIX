package com.nutritonix.api_gateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    private static final List<String> EXCLUDED_PATHS = List.of(
            "/auth/register",
            "/auth/token",
            "/auth/validate",
            "/eureka",
            "/v3/api-docs",
            "/swagger-ui",
            "/swagger-ui/",
            "/swagger-ui/index.html",
            "/user/swagger-ui",
            "/user/swagger-ui/",
            "/user/swagger-ui/index.html",
            "/user/v3/api-docs",
            "/user/webjars",
            "/nutrition/v3/api-docs",
            "/wishlist/v3/api-docs",
            "/auth/v3/api-docs"
    );

    public Predicate<ServerHttpRequest> isSecured = request -> {
        String requestPath = request.getURI().getPath();
        System.out.println(" API Gateway Filtering Request Path: " + requestPath);

        boolean isExcludedRequest = EXCLUDED_PATHS.stream().anyMatch(requestPath::startsWith);

        if (isExcludedRequest) {
            System.out.println(" Bypassing Authentication for: " + requestPath);
        }

        return !isExcludedRequest;
    };
}
