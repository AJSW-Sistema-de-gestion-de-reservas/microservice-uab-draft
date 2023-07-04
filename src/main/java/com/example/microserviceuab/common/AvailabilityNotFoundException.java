package com.example.microserviceuab.common;

public class AvailabilityNotFoundException extends RuntimeException {

    public AvailabilityNotFoundException(String message) {
        super(message);
    }

    public AvailabilityNotFoundException() {
        super();
    }

}
