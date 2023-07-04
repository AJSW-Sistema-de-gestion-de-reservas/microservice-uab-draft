package com.example.microserviceuab.accommodation;

import com.example.microserviceuab.common.AccommodationNotFoundException;
import com.example.microserviceuab.common.OwnerNotFoundException;
import com.example.microserviceuab.user.Owner;
import com.example.microserviceuab.user.OwnerRepository;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccommodationServiceImp implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final RoomRepository roomRepository;
    private final OwnerRepository ownerRepository;

    public AccommodationServiceImp(AccommodationRepository accommodationRepository,
                                   RoomRepository roomRepository,
                                   OwnerRepository ownerRepository) {
        this.accommodationRepository = accommodationRepository;
        this.roomRepository = roomRepository;
        this.ownerRepository = ownerRepository;
    }

    @Override
    public void create(AccommodationCreationWithChatIdRequestDto dto) {
        Owner owner = ownerRepository.findByChatId(123123123L).orElseThrow(OwnerNotFoundException::new);
        saveAccommodation(owner, dto.getName(), dto.getAddress(), dto.getCity(), dto.getProvince(), dto.getPostalCode());
    }

    @Override
    public void create(AccommodationCreationWithIdRequestDto dto) {
        Owner owner = ownerRepository.findById(dto.getOwnerId()).orElseThrow(OwnerNotFoundException::new);
        saveAccommodation(owner, dto.getName(), dto.getAddress(), dto.getCity(), dto.getProvince(), dto.getPostalCode());
    }

    private void saveAccommodation(Owner owner, String name, String address, String city, String province, String postalCode) {
        Accommodation accommodation = Accommodation.builder()
                .owner(owner)
                .name(name)
                .address(address)
                .city(city)
                .province(province)
                .postalCode(postalCode)
                .enabled(true)
                .build();

        accommodationRepository.save(accommodation);
    }

    @Override
    public void update(String id, AccommodationUpdateRequestDto dto) {
        Owner owner = ownerRepository.findById(dto.getOwnerId()).orElseThrow(OwnerNotFoundException::new);

        accommodationRepository.findById(id)
                .ifPresentOrElse((accommodation) -> {
                    if (Objects.equals(owner.getId(), dto.getOwnerId())) {
                        accommodation.setName(dto.getName());
                        accommodation.setAddress(dto.getAddress());
                        accommodation.setCity(dto.getCity());
                        accommodation.setProvince(dto.getProvince());
                    }
                }, () -> {
                    throw new RuntimeException();
                });
    }

    @Override
    public AccommodationDetailsResponseDto findById(String id) {
        Optional<Accommodation> result = accommodationRepository.findById(id);

        if (result.isEmpty())
            throw new AccommodationNotFoundException();

        Accommodation accommodation = result.get();
        AccommodationDetailsResponseDto.AccommodationDetailsResponseDtoBuilder builder = AccommodationDetailsResponseDto.builder()
                .id(accommodation.getId())
                .name(accommodation.getName())
                .address(accommodation.getAddress())
                .city(accommodation.getCity())
                .province(accommodation.getProvince())
                .postalCode(accommodation.getPostalCode());

        List<RoomInfoResponseDto> rooms = roomRepository.findAllByAccommodation(new ObjectId(accommodation.getId()))
                .stream().map(r -> RoomInfoResponseDto.builder()
                        .id(r.getId())
                        .name(r.getName())
                        .maxPeople(r.getMaxPeople())
                        .quantity(r.getQuantity())
                        .price(r.getPrice())
                        .build())
                .toList();
        builder.rooms(rooms);

        return builder.build();
    }

    @Override
    public List<AccommodationInfoResponseDto> findByOwnerId(String ownerId) {
        List<Accommodation> resultList = accommodationRepository.findAllByOwnerId(new ObjectId(ownerId));

        return resultList.stream().map(a -> AccommodationInfoResponseDto.builder()
                .id(a.getId())
                .name(a.getName())
                .address(a.getAddress())
                .city(a.getCity())
                .province(a.getProvince())
                .postalCode(a.getPostalCode())
                .build()).toList();
    }

    @Override
    public List<AccommodationInfoResponseDto> findByName(String name) {
        List<Accommodation> resultList = accommodationRepository.findAllByName(name);

        return resultList.stream().map(a -> AccommodationInfoResponseDto.builder()
                .id(a.getId())
                .name(a.getName())
                .address(a.getAddress())
                .city(a.getCity())
                .province(a.getProvince())
                .postalCode(a.getPostalCode())
                .build()).toList();
    }

    @Override
    public List<AccommodationInfoResponseDto> findByCity(String city) {
        List<Accommodation> resultList = accommodationRepository.findAllByCity(city);

        return resultList.stream().map(a -> AccommodationInfoResponseDto.builder()
                .id(a.getId())
                .name(a.getName())
                .address(a.getAddress())
                .city(a.getCity())
                .province(a.getProvince())
                .postalCode(a.getPostalCode())
                .build()).toList();
    }

    @Override
    public List<AccommodationInfoResponseDto> findByProvince(String province) {
        List<Accommodation> resultList = accommodationRepository.findAllByProvince(province);

        return resultList.stream().map(a -> AccommodationInfoResponseDto.builder()
                .id(a.getId())
                .name(a.getName())
                .address(a.getAddress())
                .city(a.getCity())
                .province(a.getProvince())
                .postalCode(a.getPostalCode())
                .build()).toList();
    }

    @Override
    public List<AccommodationInfoResponseDto> findByNameCityAndProvince(String name, String city, String province) {
        TextCriteria criteria = TextCriteria.forDefaultLanguage()
                .caseSensitive(false)
                .diacriticSensitive(false)
                .matching(name)
                .matchingPhrase(city)
                .matchingPhrase(province);

        List<Accommodation> resultList = accommodationRepository.findAllBy(criteria);

        return resultList.stream().map(a -> AccommodationInfoResponseDto.builder()
                .id(a.getId())
                .name(a.getName())
                .address(a.getAddress())
                .city(a.getCity())
                .province(a.getProvince())
                .postalCode(a.getPostalCode())
                .build()).toList();
    }
}
