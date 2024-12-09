package com.immfly.java_backend_test.business.exception;

public class OrderNotFoundException extends EntityNotFoundException {

    public OrderNotFoundException(String message) {
        super(message);
    }

}
