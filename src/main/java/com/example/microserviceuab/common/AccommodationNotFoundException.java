package com.example.microserviceuab.common;

public class AccommodationNotFoundException extends RuntimeException {

    public AccommodationNotFoundException(String message) {
        super(message);
    }

    public AccommodationNotFoundException() {
        super();
    }

}
