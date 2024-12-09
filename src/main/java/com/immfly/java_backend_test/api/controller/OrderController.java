package com.immfly.java_backend_test.api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.immfly.java_backend_test.api.dto.OrderInputDto;
import com.immfly.java_backend_test.api.dto.OrderOutputDto;
import com.immfly.java_backend_test.api.dto.PayOrderDto;
import com.immfly.java_backend_test.api.mapper.OrderMapper;
import com.immfly.java_backend_test.business.service.OrderService;
import com.immfly.java_backend_test.domain.entity.Order;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/orders")
@Tag(name = "Orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderOutputDto>> getOrders() {
        return ResponseEntity.ok(OrderMapper.toOutputDtoList(orderService.getAllOrders()));
    }

    @PostMapping
    public ResponseEntity<OrderOutputDto> saveOrder(@RequestBody OrderInputDto orderInputDto) {
        Order newOrder = OrderMapper.fromInputDto(orderInputDto);
        return ResponseEntity.ok(OrderMapper.toOutputDto(orderService.saveOrder(newOrder)));
    }

    @DeleteMapping("/cancelOrder/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable("orderId") UUID orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok("Order cancelled successfully.");
    }

    @PatchMapping("/addProduct/{orderId}/{productId}")
    public ResponseEntity<OrderOutputDto> addProduct(@PathVariable("orderId") UUID orderId, @PathVariable("productId") UUID productId) {
        return ResponseEntity.ok(OrderMapper.toOutputDto(orderService.addProduct(orderId, productId)));
    }

    @PatchMapping("/finishOrder/{orderId}")
    public ResponseEntity<String> finishOrder(@PathVariable("orderId") UUID orderId, @RequestBody PayOrderDto payOrderDto) {
        Order.PaymentStatus paymentStatus = orderService.finishOrder(orderId, payOrderDto.getCardToken(), payOrderDto.getPaymentGateway(), payOrderDto.getBuyerEmail());
        if (Order.PaymentStatus.PAYMENT_FAILED.equals(paymentStatus)) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Payment failed.");
        }
        return ResponseEntity.ok("Order finished successfully. Payment status: " + paymentStatus);
    }

}
