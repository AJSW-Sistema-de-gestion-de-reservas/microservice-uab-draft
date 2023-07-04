package com.example.microserviceuab.common;

public class NoAvailabilityException extends RuntimeException {

    public NoAvailabilityException(String message) {
        super(message);
    }

    public NoAvailabilityException() {
        super();
    }

}
