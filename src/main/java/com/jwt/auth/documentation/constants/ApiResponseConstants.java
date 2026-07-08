package com.jwt.auth.documentation.constants;

public final class ApiResponseConstants {

    public static final String OK = "200";
    public static final String CREATED = "201";
    public static final String NO_CONTENT = "204";
    public static final String BAD_REQUEST = "400";
    public static final String UNAUTHORIZED = "401";
    public static final String FORBIDDEN = "403";
    public static final String NOT_FOUND = "404";
    public static final String CONFLICT = "409";
    public static final String TOO_MANY_REQUESTS = "429";
    public static final String INTERNAL_SERVER_ERROR = "500";

    public static final String DESC_OK = "Operation Completed Successfully.";
    public static final String DESC_CREATED = "Resource created successfully.";
    public static final String DESC_NO_CONTENT = "Resource deleted successfully.";
    public static final String DESC_BAD_REQUEST = "Validation failed.";
    public static final String DESC_UNAUTHORIZED = "Authentication required.";
    public static final String DESC_FORBIDDEN = "Access denied.";
    public static final String DESC_NOT_FOUND = "Resource not found.";
    public static final String DESC_CONFLICT = "Resource already exists.";
    public static final String DESC_TOO_MANY_REQUESTS = "Too many requests.";
    public static final String DESC_INTERNAL_SERVER_ERROR = "Unexpected server error.";

    private ApiResponseConstants() {}
}
