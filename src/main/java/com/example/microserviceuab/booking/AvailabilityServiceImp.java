package com.example.microserviceuab.booking;

import com.example.microserviceuab.accommodation.Accommodation;
import com.example.microserviceuab.accommodation.Room;
import com.example.microserviceuab.accommodation.RoomRepository;
import com.example.microserviceuab.common.AvailabilityAlreadyExistsException;
import com.example.microserviceuab.common.AvailabilityNotFoundException;
import com.example.microserviceuab.common.RoomNotFoundException;
import com.example.microserviceuab.common.TimeUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AvailabilityServiceImp implements AvailabilityService {
    private final AvailabilityRepository availabilityRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public AvailabilityServiceImp(AvailabilityRepository availabilityRepository, RoomRepository roomRepository) {
        this.availabilityRepository = availabilityRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public void register(String accommodationId, String roomId, AvailabilityCreateRequestDto dto) {
        Room room = roomRepository.findByIdAndAccommodation(roomId, new ObjectId(accommodationId))
                .orElseThrow(RoomNotFoundException::new);

        if (availabilityRepository.existsByAccommodationIdRoomIdAndDate(
                new ObjectId(accommodationId),
                new ObjectId(roomId),
                TimeUtils.convertInstantDateToUTC(dto.getDate())))
            throw new AvailabilityAlreadyExistsException();

        Availability availability = Availability.builder()
                .accommodation(Accommodation.builder().id(accommodationId).build())
                .room(room)
                .date(dto.getDate())
                .availableQuantity(room.getQuantity())
                .build();

        availabilityRepository.save(availability);
    }

    @Override
    public List<AvailabilityInfoResponseDto> findByAccommodationIdAndRoomId(String accommodationId, String roomId) {
        List<Availability> results = availabilityRepository.findAllByAccommodationAndRoom(
                new ObjectId(accommodationId), new ObjectId(roomId)
        );

        return results.stream().map(availability -> AvailabilityInfoResponseDto.builder()
                .id(availability.getId())
                .accommodationId(availability.getAccommodation().getId())
                .roomId(availability.getRoom().getId())
                .date(availability.getDate())
                .availableQuantity(availability.getAvailableQuantity())
                .build()).toList();
    }

    @Override
    public AvailabilityInfoResponseDto findByAccommodationIdRoomIdAndDate(String accommodationId, String roomId,
                                                                          Date date) {
        Availability availability = availabilityRepository.findByAccommodationAndRoomAndDate(
                new ObjectId(accommodationId), new ObjectId(roomId),
                TimeUtils.convertInstantDateToUTC(date)
        ).orElseThrow(AvailabilityNotFoundException::new);

        return AvailabilityInfoResponseDto.builder()
                .id(availability.getId())
                .accommodationId(availability.getAccommodation().getId())
                .roomId(availability.getRoom().getId())
                .date(availability.getDate())
                .availableQuantity(availability.getAvailableQuantity())
                .build();
    }

    @Override
    public List<AvailabilityInfoResponseDto> findByAccommodationIdRoomIdAndDates(String accommodationId, String roomId,
                                                                                 Date startDate, Date endDate) {
        List<Availability> results = availabilityRepository.findAllByAccommodationAndRoomAndDateBetween(
                new ObjectId(accommodationId),
                new ObjectId(roomId),
                TimeUtils.convertInstantDateToUTC(startDate),
                TimeUtils.convertInstantDateToUTC(endDate)
        );

        return results.stream().map(availability -> AvailabilityInfoResponseDto.builder()
                .id(availability.getId())
                .accommodationId(availability.getAccommodation().getId())
                .roomId(availability.getRoom().getId())
                .date(availability.getDate())
                .availableQuantity(availability.getAvailableQuantity())
                .build()).toList();
    }

    @Override
    public List<AvailabilityInfoResponseDto> findByAccommodationId(String accommodationId) {
        List<Availability> results = availabilityRepository.findAllByAccommodation(new ObjectId(accommodationId));

        return results.stream().map(availability -> AvailabilityInfoResponseDto.builder()
                .id(availability.getId())
                .accommodationId(availability.getAccommodation().getId())
                .roomId(availability.getRoom().getId())
                .date(availability.getDate())
                .availableQuantity(availability.getAvailableQuantity())
                .build()).toList();
    }

    @Override
    public List<AvailabilityInfoResponseDto> findByAccommodationIdAndDate(String accommodationId, Date date) {
        List<Availability> results = availabilityRepository.findAllByAccommodationAndDate(
                new ObjectId(accommodationId),
                TimeUtils.convertInstantDateToUTC(date)
        );

        return results.stream().map(availability -> AvailabilityInfoResponseDto.builder()
                .id(availability.getId())
                .accommodationId(availability.getAccommodation().getId())
                .roomId(availability.getRoom().getId())
                .date(availability.getDate())
                .availableQuantity(availability.getAvailableQuantity())
                .build()).toList();
    }

    @Override
    public List<AvailabilityInfoResponseDto> findByAccommodationIdAndDates(String accommodationId, Date startDate, Date endDate) {
        List<Availability> results = availabilityRepository.findAllByAccommodationAndDateBetween(
                new ObjectId(accommodationId),
                TimeUtils.convertInstantDateToUTC(startDate),
                TimeUtils.convertInstantDateToUTC(endDate)
        );

        return results.stream().map(availability -> AvailabilityInfoResponseDto.builder()
                .id(availability.getId())
                .accommodationId(availability.getAccommodation().getId())
                .roomId(availability.getRoom().getId())
                .date(availability.getDate())
                .availableQuantity(availability.getAvailableQuantity())
                .build()).toList();
    }
}
