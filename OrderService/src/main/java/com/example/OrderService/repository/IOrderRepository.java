package com.example.OrderService.repository;

import com.example.OrderService.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

// Interface extending JpaRepository for CRUD operations on Order entities
public interface IOrderRepository extends JpaRepository<Order, Long> {
}
