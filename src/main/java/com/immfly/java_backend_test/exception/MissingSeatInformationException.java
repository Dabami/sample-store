package com.immfly.java_backend_test.exception;

public class MissingSeatInformationException extends RuntimeException {

    public MissingSeatInformationException(String message) {
        super(message);
    }

}
