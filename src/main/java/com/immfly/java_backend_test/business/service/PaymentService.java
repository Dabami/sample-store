package com.immfly.java_backend_test.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.immfly.java_backend_test.business.port.PaymentGateway;
import com.immfly.java_backend_test.domain.entity.Order;

@Service
public class PaymentService {

    @Autowired
    PaymentGateway paymentGateway;

    public Order.PaymentStatus payOrder(Order order) {
        return paymentGateway.processPayment(order.getCardToken(), order.getTotalPrice(), order.getPaymentGateway());
    }

}
