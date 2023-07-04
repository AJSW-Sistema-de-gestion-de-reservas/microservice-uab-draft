package com.example.microserviceuab.booking;

import lombok.Data;

import java.util.Date;

@Data
public class BookingCreationRequestDto {
    private Date checkIn;
    private Date checkOut;
    private String clientId;
}
