package com.saurabh.ecommerce.order_service.controllers;

import com.saurabh.ecommerce.order_service.config.FeaturesEnableConfig;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.saurabh.ecommerce.order_service.dto.OrderRequestDto;
import com.saurabh.ecommerce.order_service.service.OrdersService;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
@Slf4j
@RefreshScope
public class OrdersController {

    private final OrdersService orderService;
    private final FeaturesEnableConfig featuresEnableConfig;

    @Value("${my.variable}")
    private String myVariable;

    @GetMapping("/currentProfile")
    public String currentProfile() {
        // return "current profile is: " + myVariable;
        if (featuresEnableConfig.isUserTrackingEnabled()) {
            log.info("User tracking enabled wohoo, my variable is: {}", myVariable);
            return "User tracking enabled wohoo, my variable is: "+ myVariable;
        } else {
            log.info("User tracking disabled awww, my variable is: {}", myVariable);
            return "User tracking disabled awww, my variable is: "+ myVariable;
        }
    }

    @GetMapping("/helloOrders")
    public String helloOrders(@RequestHeader("X-User-Id") Long userId) {

        return "Hello from Orders Service, userId is: " + userId;
    }

    @PostMapping("/create-order")
    public ResponseEntity<OrderRequestDto> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        OrderRequestDto orderRequestDto1 = orderService.createOrder(orderRequestDto);
        return ResponseEntity.ok(orderRequestDto1);
    }

    @GetMapping
    public ResponseEntity<List<OrderRequestDto>> getAllOrders(HttpServletRequest httpServletRequest) {
        log.info("Fetching all orders via controller");
        List<OrderRequestDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);  // Returns 200 OK with the list of orders
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderRequestDto> getOrderById(@PathVariable Long id) {
        log.info("Fetching order with ID: {} via controller", id);
        OrderRequestDto order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);  // Returns 200 OK with the order
    }
}