package com.example.microserviceuab.user;

import lombok.Data;

@Data
public class ClientCreationRequestDto {
    private String username;
    private String firstName;
    private String lastName;
    private long chatId;
}
