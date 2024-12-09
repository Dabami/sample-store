package com.immfly.java_backend_test.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.immfly.java_backend_test.business.port.PaymentGateway;
import com.immfly.java_backend_test.domain.entity.Order;

@Configuration
public class MockPaymentGatewayConfig {

    @Bean
    public PaymentGateway paymentGateway() {
        PaymentGateway mockGateway = Mockito.mock(PaymentGateway.class);

        Mockito.when(mockGateway.processPayment(Mockito.anyString(), Mockito.anyFloat(), Mockito.eq("ING"))).thenReturn(Order.PaymentStatus.PAID);
        Mockito.when(mockGateway.processPayment(Mockito.isNull(), Mockito.anyFloat(), Mockito.eq("CASH"))).thenReturn(Order.PaymentStatus.OFFLINE_PAYMENT);
        Mockito.when(mockGateway.processPayment(Mockito.anyString(), Mockito.anyFloat(), Mockito.isNull())).thenReturn(Order.PaymentStatus.PAYMENT_FAILED);
        Mockito.when(mockGateway.processPayment(Mockito.isNull(), Mockito.anyFloat(), Mockito.eq("ING"))).thenReturn(Order.PaymentStatus.PAYMENT_FAILED);

        return mockGateway;
    }
}
