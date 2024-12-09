package com.immfly.java_backend_test.business.exception;

public class OrderNotOpenException extends BusinessException {

    public OrderNotOpenException(String message) {
        super(message);
    }

}
