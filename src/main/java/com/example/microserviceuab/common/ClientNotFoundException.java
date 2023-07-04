package com.example.microserviceuab.common;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException(String message) {
        super(message);
    }

    public ClientNotFoundException() {
        super();
    }

}
