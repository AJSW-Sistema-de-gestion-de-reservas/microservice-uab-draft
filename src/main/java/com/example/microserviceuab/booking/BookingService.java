package com.example.microserviceuab.booking;

import java.util.Date;
import java.util.List;

public interface BookingService {
    void register(String accommodationId, String roomId, BookingCreationRequestDto dto);

    List<BookingInfoResponseDto> findAllByAccommodation(String accommodationId);

    List<BookingInfoResponseDto> findAllByAccommodationAndDate(String accommodationId, Date date);

    List<BookingInfoResponseDto> findAllByAccommodationAndDateBetween(String accommodationId, Date startDate,
                                                                      Date endDate);

    List<BookingInfoResponseDto> findAllByAccommodationAndRoom(String accommodationId, String roomId);

    List<BookingInfoResponseDto> findAllByAccommodationAndRoomAndDate(String accommodationId, String roomId, Date date);

    List<BookingInfoResponseDto> findAllByAccommodationAndRoomAndDateBetween(String accommodationId, String roomId,
                                                                             Date startDate, Date endDate);

    List<BookingInfoResponseDto> findAllByClient(String clientId);
}
