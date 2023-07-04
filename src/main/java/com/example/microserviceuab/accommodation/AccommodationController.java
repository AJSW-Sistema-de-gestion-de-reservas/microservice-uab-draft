package com.example.microserviceuab.accommodation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/accommodation")
public class AccommodationController {
    private final AccommodationService service;

    @Autowired
    public AccommodationController(AccommodationService service) {
        this.service = service;
    }

    @PostMapping("chatId")
    public ResponseEntity<String> createWithChatId(@RequestBody AccommodationCreationWithChatIdRequestDto dto) {
        service.create(dto);
        return ResponseEntity.ok("");
    }

    @PostMapping("")
    public ResponseEntity<String> createWithId(@RequestBody AccommodationCreationWithIdRequestDto dto) {
        service.create(dto);
        return ResponseEntity.ok("");
    }

    @PutMapping("{id}")
    public ResponseEntity<String> update(@PathVariable String id, @RequestBody AccommodationUpdateRequestDto dto) {
        service.update(id, dto);
        return ResponseEntity.ok("");
    }

    @GetMapping("{id}")
    public ResponseEntity<AccommodationDetailsResponseDto> getById(@PathVariable String id) {
        AccommodationDetailsResponseDto response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("search/owner={ownerId}")
    public ResponseEntity<List<AccommodationInfoResponseDto>> searchByOwnerId(@PathVariable String ownerId) {
        List<AccommodationInfoResponseDto> result = service.findByOwnerId(ownerId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("search/name={name}")
    public ResponseEntity<List<AccommodationInfoResponseDto>> searchByName(@PathVariable String name) {
        List<AccommodationInfoResponseDto> result = service.findByName(name);
        return ResponseEntity.ok(result);
    }

    @GetMapping("search/city={city}")
    public ResponseEntity<List<AccommodationInfoResponseDto>> searchByCity(@PathVariable String city) {
        List<AccommodationInfoResponseDto> result = service.findByCity(city);
        return ResponseEntity.ok(result);
    }

    @GetMapping("search/province={province}")
    public ResponseEntity<List<AccommodationInfoResponseDto>> searchByProvince(@PathVariable String province) {
        List<AccommodationInfoResponseDto> result = service.findByProvince(province);
        return ResponseEntity.ok(result);
    }

    @GetMapping("search")
    public ResponseEntity<List<AccommodationInfoResponseDto>> searchByNameCityAndProvince(
            @RequestParam(name = "name", required = false, defaultValue = "") String name,
            @RequestParam(name = "city", required = false, defaultValue = "") String city,
            @RequestParam(name = "province", required = false, defaultValue = "") String province) {
        List<AccommodationInfoResponseDto> result = service.findByNameCityAndProvince(name, city, province);
        return ResponseEntity.ok(result);
    }
}
