package com.immfly.java_backend_test.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.immfly.java_backend_test.api.dto.OrderInputDto;
import com.immfly.java_backend_test.api.dto.OrderOutputDto;
import com.immfly.java_backend_test.api.mapper.OrderMapper;
import com.immfly.java_backend_test.domain.entity.Order;
import com.immfly.java_backend_test.service.OrderService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/orders")
@Tag(name = "Orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderOutputDto>> getOrders() {
        return ResponseEntity.ok(OrderMapper.toDtoList(orderService.getAllOrders()));
    }

    @PostMapping
    public ResponseEntity<OrderOutputDto> saveOrder(@RequestBody OrderInputDto orderInputDto) {
        Order newOrder = OrderMapper.fromDto(orderInputDto);
        return ResponseEntity.ok(OrderMapper.toDto(orderService.savOrder(newOrder)));
    }

}
