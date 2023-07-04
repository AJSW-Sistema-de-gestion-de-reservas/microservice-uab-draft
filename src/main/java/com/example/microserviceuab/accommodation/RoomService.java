package com.example.microserviceuab.accommodation;

import java.util.List;

public interface RoomService {
    void create(String accommodationId, RoomCreationRequestDto dto);

    List<RoomInfoResponseDto> getByAccommodationId(String accommodationId);
}
