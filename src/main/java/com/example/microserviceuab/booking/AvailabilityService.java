package com.example.microserviceuab.booking;

import java.util.Date;
import java.util.List;

public interface AvailabilityService {
    void register(String accommodationId, String roomId, AvailabilityCreateRequestDto dto);

    List<AvailabilityInfoResponseDto> findByAccommodationIdAndRoomId(String accommodationId, String roomId);

    AvailabilityInfoResponseDto findByAccommodationIdRoomIdAndDate(String accommodationId, String roomId, Date date);

    List<AvailabilityInfoResponseDto> findByAccommodationIdRoomIdAndDates(String accommodationId, String roomId,
                                                                          Date startDate, Date endDate);

    List<AvailabilityInfoResponseDto> findByAccommodationId(String accommodationId);

    List<AvailabilityInfoResponseDto> findByAccommodationIdAndDate(String accommodationId, Date date);

    List<AvailabilityInfoResponseDto> findByAccommodationIdAndDates(String accommodationId, Date startDate,
                                                                    Date endDate);
}
