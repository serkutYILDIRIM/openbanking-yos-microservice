package com.openbanking.yos.common.exception;

public class OhvpsException extends RuntimeException {

    public OhvpsException(String message) {
        super(message);
    }

    public OhvpsException(String message, Throwable cause) {
        super(message, cause);
    }
}

