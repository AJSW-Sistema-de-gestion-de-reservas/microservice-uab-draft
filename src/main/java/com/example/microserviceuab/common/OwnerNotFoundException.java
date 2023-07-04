package com.example.microserviceuab.common;

public class OwnerNotFoundException extends RuntimeException {

    public OwnerNotFoundException(String message) {
        super(message);
    }

    public OwnerNotFoundException() {
        super();
    }

}
