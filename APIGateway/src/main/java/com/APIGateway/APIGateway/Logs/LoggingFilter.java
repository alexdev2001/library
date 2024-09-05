package com.APIGateway.APIGateway.Logs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.springframework.core.Ordered;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;

// custom filter for logging requests to the API Gateway

// implement interfaces to intercept requests
@Component
public class LoggingFilter implements GlobalFilter, Ordered {

    // create a logger instance
    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    // a filter of Mono type which returns a single value asynchronously
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestPath = exchange.getRequest().getPath().toString();
        // log request from the request variable that was just declared
        logger.info("Request received for path: {}", requestPath);

        // after the request is executed log the response to the request
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            logger.info("Response sent for path: {}", requestPath);
        }));
    }

    // define a function that defines the order of execution of the filter in the request/response cycle
    @Override
    public int getOrder() {
        return -1;
    }


}

