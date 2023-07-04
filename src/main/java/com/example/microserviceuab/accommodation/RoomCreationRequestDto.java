package com.example.microserviceuab.accommodation;

import lombok.Data;

@Data
public class RoomCreationRequestDto {
    private String name;
    private int maxPeople;
    private int quantity;
    private double price;
}
