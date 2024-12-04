package com.immfly.java_backend_test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.immfly.java_backend_test.domain.entity.Order;
import com.immfly.java_backend_test.domain.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public Iterable<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order savOrder(Order order) {
        return orderRepository.save(order);
    }

}
