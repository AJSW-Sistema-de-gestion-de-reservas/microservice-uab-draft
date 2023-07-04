package com.example.microserviceuab.accommodation;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccommodationCreationWithChatIdRequestDto {
    private String name;
    private String address;
    private String city;
    private String province;
    private String postalCode;
    private long ownerChatId;
}
