package com.example.microserviceuab.accommodation;

import com.example.microserviceuab.common.AccommodationNotFoundException;
import com.example.microserviceuab.common.RoomAlreadyExistsException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImp implements RoomService {
    private final RoomRepository roomRepository;
    private final AccommodationRepository accommodationRepository;

    @Autowired
    public RoomServiceImp(RoomRepository roomRepository, AccommodationRepository accommodationRepository) {
        this.roomRepository = roomRepository;
        this.accommodationRepository = accommodationRepository;
    }

    @Override
    public void create(String accommodationId, RoomCreationRequestDto dto) {
        if (!accommodationRepository.existsById(accommodationId))
            throw new AccommodationNotFoundException();

        if (roomRepository.existsByName(dto.getName()))
            throw new RoomAlreadyExistsException();

        Accommodation accommodation = accommodationRepository.findById(accommodationId)
                .orElseThrow(RuntimeException::new);

        Room room = Room.builder()
                .name(dto.getName())
                .quantity(dto.getQuantity())
                .maxPeople(dto.getMaxPeople())
                .price(dto.getPrice())
                .enabled(true)
                .accommodation(accommodation)
                .build();

        roomRepository.save(room);
    }

    @Override
    public List<RoomInfoResponseDto> getByAccommodationId(String accommodationId) {
        List<Room> results = roomRepository.findAllByAccommodation(new ObjectId(accommodationId));

        return results.stream().map(room -> RoomInfoResponseDto.builder()
                .id(room.getId())
                .name(room.getName())
                .maxPeople(room.getMaxPeople())
                .quantity(room.getQuantity())
                .price(room.getPrice())
                .build()).toList();
    }
}
