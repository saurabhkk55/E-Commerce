package com.saurabh.ecommerce.order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.saurabh.ecommerce.order_service.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
