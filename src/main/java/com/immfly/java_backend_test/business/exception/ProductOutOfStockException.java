package com.immfly.java_backend_test.business.exception;

public class ProductOutOfStockException extends BusinessException {

    public ProductOutOfStockException(String message) {
        super(message);
    }

}
