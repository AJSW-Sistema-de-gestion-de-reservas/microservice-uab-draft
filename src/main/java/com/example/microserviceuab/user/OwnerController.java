package com.example.microserviceuab.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/owner")
public class OwnerController {
    private final OwnerService service;

    @Autowired
    public OwnerController(OwnerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> register(@RequestBody OwnerCreationRequestDto dto) {
        service.register(dto);
        return ResponseEntity.ok("");
    }

    @GetMapping("{id}")
    public ResponseEntity<OwnerInfoResponseDto> getInfoFromId(@PathVariable String id) {
        OwnerInfoResponseDto dto = service.getInfoFromId(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("chatId={chatId}")
    public ResponseEntity<OwnerInfoResponseDto> getInfoFromChatId(@PathVariable long chatId) {
        OwnerInfoResponseDto dto = service.getInfoFromChatId(chatId);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("username={username}")
    public ResponseEntity<OwnerInfoResponseDto> getInfoFromUsername(@PathVariable String username) {
        OwnerInfoResponseDto dto = service.getInfoFromUsername(username);
        return ResponseEntity.ok(dto);
    }
}
