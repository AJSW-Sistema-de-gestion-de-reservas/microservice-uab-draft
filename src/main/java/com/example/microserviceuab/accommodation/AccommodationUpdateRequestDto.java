package com.example.microserviceuab.accommodation;

import lombok.Data;

@Data
public class AccommodationUpdateRequestDto {
    private String name;
    private String address;
    private String city;
    private String province;
    private String postalCode;
    private String ownerId;
}
