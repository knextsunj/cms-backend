package com.github.knextsunj.cms.exception;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public DataNotFoundException(String message) {
        this(message, null);
    }
}
