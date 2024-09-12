package com.APIGateway.APIGateway.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;

@Component
public class JwtAuthenticationFilter implements GlobalFilter {

    @Value("${secret}")
    private String secretkey;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        logger.info("Incoming request: {}", request.getURI());

        // Check if the authorization header is present in the request header
        if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            logger.warn("Authorization header is missing in the request");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        logger.info("Authorization header found: {}", authHeader);

        // Extract token by removing "Bearer "
        String token = authHeader.substring(7);
        logger.debug("Token extracted: {}", token);

        try {
            Claims claims = Jwts.parser()
                    .verifyWith(getSignInkey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            logger.info("JWT token successfully parsed. User ID: {}", claims.getSubject());

            // Add user ID to the request headers
            request = request.mutate().header("x-user-id", claims.getSubject()).build();
            exchange = exchange.mutate().request(request).build();

        } catch (Exception e) {
            logger.error("JWT validation failed: {}", e.getMessage());
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // Proceed with the filter chain
        return chain.filter(exchange);
    }

    private SecretKey getSignInkey() {
        logger.debug("Decoding secret key");
        byte[] keyBytes = Decoders.BASE64.decode(secretkey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}