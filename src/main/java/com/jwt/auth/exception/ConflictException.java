package com.jwt.auth.exception;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }

    public ConflictException(String resourceName, String value) {
        super(
                String.format("%s : %s already exists", resourceName, value)
        );
    }
}
