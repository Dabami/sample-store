package com.immfly.java_backend_test.exception;

public class OrderNotOpenException extends RuntimeException {

    public OrderNotOpenException(String message) {
        super(message);
    }

}
