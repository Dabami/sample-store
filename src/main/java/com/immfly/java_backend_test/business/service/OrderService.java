package com.immfly.java_backend_test.business.service;

import java.sql.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.immfly.java_backend_test.business.exception.MissingSeatInformationException;
import com.immfly.java_backend_test.business.exception.OrderNotFoundException;
import com.immfly.java_backend_test.business.exception.OrderNotOpenException;
import com.immfly.java_backend_test.business.exception.ProductOutOfStockException;
import com.immfly.java_backend_test.domain.entity.Order;
import com.immfly.java_backend_test.domain.entity.Product;
import com.immfly.java_backend_test.domain.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductService productService;
    @Autowired
    PaymentService paymentService;

    public Iterable<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(UUID id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order with id " + id + " not found."));
    }

    public Order saveOrder(Order order) {
        checkSeatPosition(order);
        return orderRepository.save(order);
    }

    public void cancelOrder(UUID id) {
        Order order = getOrderById(id);
        checkOrderOpen(order);
        order.setStatus(Order.Status.DROPPED);
        for (Product product : order.getProducts()) {
            product.setStock(product.getStock() + 1);
            productService.saveProduct(product);
        }
        orderRepository.save(order);
    }

    public Order.PaymentStatus finishOrder(UUID id, String cardToken, String paymentGateway, String buyerEmail) {
        Order order = getOrderById(id);
        checkOrderOpen(order);
        order.setBuyerEmail(buyerEmail);
        order.setCardToken(cardToken);
        order.setPaymentGateway(paymentGateway);
        Order.PaymentStatus paymentStatus = paymentService.payOrder(order);
        order.setPaymentStatus(paymentStatus);
        if (!Order.PaymentStatus.PAYMENT_FAILED.equals(paymentStatus)) {
            order.setPaymentDate(new Date(System.currentTimeMillis()));
            order.setStatus(Order.Status.FINISHED);
        }
        orderRepository.save(order);
        return paymentStatus;
    }

    public Order addProduct(UUID orderId, UUID productId) {
        Order order = getOrderById(orderId);
        Product product = productService.getProductById(productId);

        checkProductAvailability(product);
        
        order.getProducts().add(product);
        order.setTotalPrice(order.getTotalPrice() + product.getPrice());
        product.setStock(product.getStock() - 1);
        productService.saveProduct(product);

        return orderRepository.save(order);
    }

    private void checkSeatPosition(Order order) {
        if (order.getSeatLetter() == null) {
            throw new MissingSeatInformationException("Seat letter is required.");
        }
        if (order.getSeatNumber() == null) {
            throw new MissingSeatInformationException("Seat number is required.");
        }
    }

    private void checkOrderOpen(Order order) {
        if (!Order.Status.OPEN.equals(order.getStatus())) {
            throw new OrderNotOpenException("Order with id " + order.getId() + " is not open.");
        }
    }

    private void checkProductAvailability(Product product) {
        if (product.getStock() <= 0) {
            throw new ProductOutOfStockException("Product with id " + product.getId() + " is out of stock.");
        }
    }

}
