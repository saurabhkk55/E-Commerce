package com.saurabh.ecommerce.api_gateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;

@Component
@Slf4j
public class GlobalLoggingFilter implements GlobalFilter, Ordered {
    /**
     * This method defines the global logging filter logic that will be applied to every request passing through the gateway.
     * It logs the request details before passing it down the filter chain and logs the response status after the request is processed.
     *
     * @param exchange The ServerWebExchange contains request and response details for a particular HTTP exchange.
     * @param chain The GatewayFilterChain allows further filters to process the request before it's routed to the destination service.
     * @return Mono<Void> which indicates completion of filter processing.
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Logging from Global Pre: {}", exchange.getRequest().getURI());
        // return chain.filter(exchange);
        return chain.filter(exchange).then(Mono.fromRunnable(() -> log.info("Logging from Global Post: {}", exchange.getResponse().getStatusCode())));
    }

    /**
     * Defines the order of the filter execution. Filters with a lower value are executed first.
     * In this case, a value of 5 means this filter will be executed after filters with a lower order value.
     *
     * @return the order value of this filter.
     */
    @Override
    public int getOrder() {
        return 5; // Determines the order of this filter in relation to other global filters
    }
}
