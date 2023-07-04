package com.example.microserviceuab.common;

public class AvailabilityAlreadyExistsException extends RuntimeException {

    public AvailabilityAlreadyExistsException(String message) {
        super(message);
    }

    public AvailabilityAlreadyExistsException() {
        super();
    }

}
