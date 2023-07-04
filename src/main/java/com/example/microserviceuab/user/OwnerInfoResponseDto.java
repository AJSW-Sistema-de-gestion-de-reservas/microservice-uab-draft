package com.example.microserviceuab.user;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OwnerInfoResponseDto {
    private String id;
    private String username;
    private long chatId;
    private String firstName;
    private String lastName;
}
