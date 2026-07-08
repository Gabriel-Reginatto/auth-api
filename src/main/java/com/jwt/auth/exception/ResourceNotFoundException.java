package com.jwt.auth.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String resourceName, String value) {
        super(
                String.format("%s %s not found", resourceName,value)
        );
    }

    public ResourceNotFoundException(String resourceName, Object identifier) {
        super(
                String.format("%s not found with ID: %s", resourceName, identifier)
        );
    }
}
