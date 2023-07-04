package com.example.microserviceuab.accommodation;

import java.util.List;

public interface AccommodationService {
    void create(AccommodationCreationWithChatIdRequestDto dto);

    void create(AccommodationCreationWithIdRequestDto dto);

    void update(String id, AccommodationUpdateRequestDto dto);

    AccommodationDetailsResponseDto findById(String id);

    List<AccommodationInfoResponseDto> findByOwnerId(String ownerId);

    List<AccommodationInfoResponseDto> findByName(String name);

    List<AccommodationInfoResponseDto> findByCity(String city);

    List<AccommodationInfoResponseDto> findByProvince(String province);

    List<AccommodationInfoResponseDto> findByNameCityAndProvince(String name, String city, String province);
}
