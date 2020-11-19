package com.deliverit.utility.exceptions;

public final class ServiceError {
    private ServiceError() {
    }

    public static final String INVALID_REQUEST = "INVALID_REQUEST";
    public static final String USER_DISABLED = "USER_DISABLED";
    public static final String UNAUTHORIZED = "Unauthorized";
    public static final String INVALID_CREDENTIALS = "INVALID_CREDENTIALS";

}
