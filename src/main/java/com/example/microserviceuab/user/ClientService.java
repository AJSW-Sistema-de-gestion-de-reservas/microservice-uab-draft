package com.example.microserviceuab.user;

public interface ClientService {
    void register(ClientCreationRequestDto dto);

    ClientInfoResponseDto getInfoFromChatId(Long chatId);

    ClientInfoResponseDto getInfoFromUsername(String username);

    ClientInfoResponseDto getInfoFromId(String id);
}
