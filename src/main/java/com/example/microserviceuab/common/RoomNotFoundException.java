package com.example.microserviceuab.common;

public class RoomNotFoundException extends RuntimeException {

    public RoomNotFoundException(String message) {
        super(message);
    }

    public RoomNotFoundException() {
        super();
    }

}
