package com.immfly.java_backend_test.business.port;

import com.immfly.java_backend_test.domain.entity.Order;

public interface PaymentGateway {

    Order.PaymentStatus processPayment(String cardToken, double amount, String paymentGateway);

}
