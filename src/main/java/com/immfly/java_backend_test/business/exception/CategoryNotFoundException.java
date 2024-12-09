package com.immfly.java_backend_test.business.exception;

public class CategoryNotFoundException extends EntityNotFoundException {

    public CategoryNotFoundException(String message) {
        super(message);
    }
    
}
