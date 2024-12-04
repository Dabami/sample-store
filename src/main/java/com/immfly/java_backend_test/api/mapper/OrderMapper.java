package com.immfly.java_backend_test.api.mapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.immfly.java_backend_test.api.dto.OrderInputDto;
import com.immfly.java_backend_test.api.dto.OrderOutputDto;
import com.immfly.java_backend_test.domain.entity.Order;

public class OrderMapper {

    public static OrderOutputDto toOutputDto(Order order) {
        return OrderOutputDto.builder()
                .id(order.getId())
                .status(order.getStatus().name())
                .products(ProductMapper.toOutputDtoList(order.getProducts()))
                .totalPrice(order.getTotalPrice())
                .cardToken(order.getCardToken())
                .paymentStatus(order.getPaymentStatus().name())
                .paymentDate(order.getPaymentDate())
                .paymentGateway(order.getPaymentGateway())
                .buyerEmail(order.getBuyerEmail())
                .seatLetter(order.getSeatLetter())
                .seatNumber(order.getSeatNumber())
                .build();
    }

    public static List<OrderOutputDto> toOutputDtoList(Iterable<Order> orders) {
        return StreamSupport.stream(orders.spliterator(), false)
                .map(OrderMapper::toOutputDto)
                .collect(Collectors.toList());
    }

    public static Order fromInputDto(OrderInputDto orderInputDto) {
        return Order.builder()
                .status(Order.Status.OPEN)
                .totalPrice(0f)
                .paymentStatus(Order.PaymentStatus.PENDING)
                .seatLetter(orderInputDto.getSeatLetter())
                .seatNumber(orderInputDto.getSeatNumber())
                .build();
    }

}
