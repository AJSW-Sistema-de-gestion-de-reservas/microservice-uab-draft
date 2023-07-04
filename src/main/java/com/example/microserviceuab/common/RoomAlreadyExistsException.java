package com.example.microserviceuab.common;

public class RoomAlreadyExistsException extends RuntimeException {

    public RoomAlreadyExistsException(String message) {
        super(message);
    }

    public RoomAlreadyExistsException() {
        super();
    }

}
