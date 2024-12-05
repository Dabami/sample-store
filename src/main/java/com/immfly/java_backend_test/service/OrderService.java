package com.immfly.java_backend_test.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.immfly.java_backend_test.domain.entity.Order;
import com.immfly.java_backend_test.domain.entity.Product;
import com.immfly.java_backend_test.domain.repository.OrderRepository;
import com.immfly.java_backend_test.exception.MissingSeatInformationException;
import com.immfly.java_backend_test.exception.OrderNotFoundException;
import com.immfly.java_backend_test.exception.OrderNotOpenException;
import com.immfly.java_backend_test.exception.ProductOutOfStockException;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductService productService;

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
        if (order.getStatus().equals(Order.Status.OPEN)) {
            order.setStatus(Order.Status.DROPPED);
            for (Product product : order.getProducts()) {
                product.setStock(product.getStock() + 1);
                productService.saveProduct(product);
            }
            orderRepository.save(order);
        } else {
            throw new OrderNotOpenException("Order with id " + id + " is not open.");
        }
    }

    public Order addProduct(UUID orderId, UUID productId) {
        Order order = getOrderById(orderId);
        Product product = productService.getProductById(productId);
        if (product.getStock() > 0) {
            order.getProducts().add(product);
            order.setTotalPrice(order.getTotalPrice() + product.getPrice());
            product.setStock(product.getStock() - 1);
            productService.saveProduct(product);
        } else {
            throw new ProductOutOfStockException("Product with id " + productId + " is out of stock.");
        }
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

}
