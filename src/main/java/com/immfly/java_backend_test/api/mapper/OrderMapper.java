package com.immfly.java_backend_test.api.mapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.immfly.java_backend_test.api.dto.OrderInputDto;
import com.immfly.java_backend_test.api.dto.OrderOutputDto;
import com.immfly.java_backend_test.domain.entity.Order;

public class OrderMapper {

    public static OrderOutputDto toDto(Order order) {
        return OrderOutputDto.builder()
                .id(order.getId())
                .status(order.getStatus().name())
                .products(ProductMapper.toDtoList(order.getProducts()))
                .totalPrice(order.getTotalPrice())
                .cardToken(order.getCardToken())
                .paymentStatus(order.getPaymentStatus().name())
                .paymentDate(order.getPaymentDate())
                .paymentGateway(order.getPaymentGateway())
                .build();
    }

    public static List<OrderOutputDto> toDtoList(Iterable<Order> orders) {
        return StreamSupport.stream(orders.spliterator(), false)
                .map(OrderMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Order fromDto(OrderInputDto orderInputDto) {
        return Order.builder()
                .cardToken(orderInputDto.getCardToken())
                .status(Order.Status.OPEN)
                .totalPrice(0f)
                .paymentGateway(orderInputDto.getPaymentGateway())
                .paymentStatus(Order.PaymentStatus.PENDING)
                .buyerEmail(orderInputDto.getBuyerEmail())
                .seatLetter(orderInputDto.getSeatLetter())
                .seatNumber(orderInputDto.getSeatNumber())
                .build();
    }

}
