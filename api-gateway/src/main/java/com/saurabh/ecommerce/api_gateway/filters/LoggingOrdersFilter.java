package com.saurabh.ecommerce.api_gateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

// Route Specific Filters
@Component
@Slf4j
public class LoggingOrdersFilter extends AbstractGatewayFilterFactory<LoggingOrdersFilter.Config> {
    /**
     * Constructor that passes the configuration class `Config` to the parent `AbstractGatewayFilterFactory` class.
     * This enables configuration binding for this specific filter if needed.
     */
    public LoggingOrdersFilter() {
        super(Config.class); // Passes the Config class to the parent constructor for potential configuration use.
    }

    /**
     * This method defines the core filtering logic for the route-specific logging filter.
     * It is applied only to specific routes (not globally), and logs the request details before proceeding to the next filter in the chain.
     *
     * @param config The configuration object (currently unused, but can be extended to handle custom config).
     * @return GatewayFilter The filter logic that logs the request URI before passing it to the next filter in the chain.
     */
    @Override
    public GatewayFilter apply(Config config) {
        // Returning the filter logic as a lambda expression
        return (exchange, chain) -> {
            // Log the URI of the incoming request for routes where this filter is applied
            log.info("Order filter pre (Route Specific Filters): {}", exchange.getRequest().getURI());
            // Continue with the next filter in the chain (or eventually route the request to its destination)
            return chain.filter(exchange);
        };
    }

    /**
     * This inner class serves as a placeholder for filter-specific configuration.
     * It allows for the possibility to add configuration options in the future without changing the core filter logic.
     */
    public static class Config {
        // Currently empty but can be extended to include specific configurations for this filter.
    }
}
