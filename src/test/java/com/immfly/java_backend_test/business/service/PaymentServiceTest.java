package com.immfly.java_backend_test.business.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.immfly.java_backend_test.business.port.PaymentGateway;
import com.immfly.java_backend_test.domain.entity.Order;

public class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private PaymentGateway paymentGateway;

    public PaymentServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void payOrder_ShouldProcessPayment_WhenDataIsValid() {
        UUID orderId = UUID.randomUUID();
        String cardToken = "5555555555555555";
        String gateway = "ING";
        Float totalPrice = 5.0f;
        Order mockOrder = Order.builder().id(orderId).cardToken(cardToken).paymentGateway(gateway).totalPrice(totalPrice).build();

        when(paymentGateway.processPayment(cardToken, totalPrice, gateway)).thenReturn(Order.PaymentStatus.PAID);

        Order.PaymentStatus paymentStatus = paymentService.payOrder(mockOrder);

        assertEquals(Order.PaymentStatus.PAID, paymentStatus);
        verify(paymentGateway, times(1)).processPayment(cardToken, totalPrice, gateway);
    }

    @Test
    void payOrder_ShouldFailPayment_WhenDataIsInvalid() {
        UUID orderId = UUID.randomUUID();
        String cardToken = null;
        String gateway = "ING";
        Float totalPrice = 5.0f;
        Order mockOrder = Order.builder().id(orderId).cardToken(cardToken).paymentGateway(gateway).totalPrice(totalPrice).build();

        when(paymentGateway.processPayment(cardToken, totalPrice, gateway)).thenReturn(Order.PaymentStatus.PAYMENT_FAILED);

        Order.PaymentStatus paymentStatus = paymentService.payOrder(mockOrder);

        assertEquals(Order.PaymentStatus.PAYMENT_FAILED, paymentStatus);
        verify(paymentGateway, times(1)).processPayment(cardToken, totalPrice, gateway);
    }

}
