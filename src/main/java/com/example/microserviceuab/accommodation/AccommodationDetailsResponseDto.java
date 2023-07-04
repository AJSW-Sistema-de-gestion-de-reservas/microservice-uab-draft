package com.example.microserviceuab.accommodation;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class AccommodationDetailsResponseDto {
    private String id;
    private String name;
    private String address;
    private String city;
    private String province;
    private String postalCode;
    private List<RoomInfoResponseDto> rooms;
}
