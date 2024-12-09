
package com.immfly.java_backend_test.business.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.immfly.java_backend_test.business.exception.ProductOutOfStockException;
import com.immfly.java_backend_test.domain.entity.Order;
import com.immfly.java_backend_test.domain.entity.Product;
import com.immfly.java_backend_test.domain.repository.OrderRepository;

class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private ProductService productService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private PaymentService paymentService;

    public OrderServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addProduct_ShouldAddProduct_WhenInStock() {
        UUID orderId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        Order mockOrder = Order.builder().id(orderId).products(new ArrayList<>()).totalPrice(0.0f).build();
        Product mockProduct = Product.builder().id(productId).stock(10).price(5.0f).build();

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(mockOrder));
        when(productService.getProductById(productId)).thenReturn(mockProduct);
        when(orderRepository.save(any(Order.class))).thenReturn(mockOrder);

        Order result = orderService.addProduct(orderId, productId);

        assertNotNull(result);
        assertEquals(1, result.getProducts().size());
        assertEquals(5.0f, result.getTotalPrice());
        verify(orderRepository, times(1)).save(mockOrder);
        verify(productService, times(1)).saveProduct(any(Product.class));
    }

    @Test
    void addProduct_ShouldThrowException_WhenOutOfStock() {
        UUID orderId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        Order mockOrder = Order.builder().id(orderId).products(new ArrayList<>()).totalPrice(0.0f).build();
        Product mockProduct = Product.builder().id(productId).stock(0).price(5.0f).build();

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(mockOrder));
        when(productService.getProductById(productId)).thenReturn(mockProduct);

        assertThrows(ProductOutOfStockException.class, () -> orderService.addProduct(orderId, productId));
        verify(orderRepository, never()).save(any(Order.class));
    }

    @Test
    void finishOrder_ShouldFinishOrder_WhenPaymentSuccess() {
        UUID orderId = UUID.randomUUID();
        String cardToken = "5555555555555555";
        String gateway = "ING";
        String buyerEmail = "buyer@mail.com";
        Order mockOrder = Order.builder().id(orderId).status(Order.Status.OPEN).build();

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(mockOrder));
        when(paymentService.payOrder(mockOrder)).thenReturn(Order.PaymentStatus.PAID);

        orderService.finishOrder(orderId, cardToken, gateway, buyerEmail);

        assertEquals(buyerEmail, mockOrder.getBuyerEmail());
        assertEquals(cardToken, mockOrder.getCardToken());
        assertEquals(gateway, mockOrder.getPaymentGateway());
        assertEquals(Order.PaymentStatus.PAID, mockOrder.getPaymentStatus());
        assertNotNull(mockOrder.getPaymentDate());
        assertEquals(Order.Status.FINISHED, mockOrder.getStatus());
        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, times(1)).save(any(Order.class));
        verify(paymentService, times(1)).payOrder(any(Order.class));
    }

    @Test
    void finishOrder_ShouldNotFinishOrder_WhenPaymentFails() {
        UUID orderId = UUID.randomUUID();
        String gateway = "ING";
        String buyerEmail = "buyer@mail.com";
        Order mockOrder = Order.builder().id(orderId).status(Order.Status.OPEN).build();

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(mockOrder));
        when(paymentService.payOrder(mockOrder)).thenReturn(Order.PaymentStatus.PAYMENT_FAILED);

        orderService.finishOrder(orderId, null, gateway, buyerEmail);

        assertEquals(buyerEmail, mockOrder.getBuyerEmail());
        assertNull(mockOrder.getCardToken());
        assertEquals(gateway, mockOrder.getPaymentGateway());
        assertEquals(Order.PaymentStatus.PAYMENT_FAILED, mockOrder.getPaymentStatus());
        assertNull(mockOrder.getPaymentDate());
        assertEquals(Order.Status.OPEN, mockOrder.getStatus());
        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, times(1)).save(any(Order.class));
        verify(paymentService, times(1)).payOrder(any(Order.class));
    }
}
