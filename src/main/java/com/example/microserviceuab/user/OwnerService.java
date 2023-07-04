package com.example.microserviceuab.user;

public interface OwnerService {
    void register(OwnerCreationRequestDto dto);

    OwnerInfoResponseDto getInfoFromChatId(Long chatId);

    OwnerInfoResponseDto getInfoFromUsername(String username);

    OwnerInfoResponseDto getInfoFromId(String id);
}
