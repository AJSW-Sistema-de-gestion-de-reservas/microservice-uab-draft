package com.example.microserviceuab.service;

import com.example.microserviceuab.user.Owner;
import com.example.microserviceuab.user.OwnerInfoResponseDto;
import com.example.microserviceuab.user.OwnerRepository;
import com.example.microserviceuab.user.OwnerServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class OwnerServiceImpTest {
    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private OwnerServiceImp ownerService;

    @Test
    public void testGetInfoFromChatId_ValidChatId_ReturnsOwnerInfoResponseDto() {
        // Arrange
        Long chatId = 12345L;
        Owner owner = new Owner();
        owner.setId("1");
        owner.setUsername("john");
        owner.setFirstName("John");
        owner.setLastName("Doe");
        owner.setChatId(chatId);

        when(ownerRepository.findByChatId(chatId)).thenReturn(Optional.of(owner));

        // Act
        OwnerInfoResponseDto responseDto = ownerService.getInfoFromChatId(chatId);

        // Assert
        assertEquals(owner.getId(), responseDto.getId());
        assertEquals(owner.getUsername(), responseDto.getUsername());
        assertEquals(owner.getFirstName(), responseDto.getFirstName());
        assertEquals(owner.getLastName(), responseDto.getLastName());
        assertEquals(owner.getChatId(), responseDto.getChatId());
    }

    @Test
    public void testGetInfoFromUsername_ValidUsername_ReturnsOwnerInfoResponseDto() {
        // Arrange
        String username = "john";
        Owner owner = new Owner();
        owner.setId("1");
        owner.setUsername(username);
        owner.setFirstName("John");
        owner.setLastName("Doe");
        owner.setChatId(12345L);

        when(ownerRepository.findByUsername(username)).thenReturn(Optional.of(owner));

        // Act
        OwnerInfoResponseDto responseDto = ownerService.getInfoFromUsername(username);

        // Assert
        assertEquals(owner.getId(), responseDto.getId());
        assertEquals(owner.getUsername(), responseDto.getUsername());
        assertEquals(owner.getFirstName(), responseDto.getFirstName());
        assertEquals(owner.getLastName(), responseDto.getLastName());
        assertEquals(owner.getChatId(), responseDto.getChatId());
    }

    @Test
    public void testGetInfoFromId_ValidId_ReturnsOwnerInfoResponseDto() {
        // Arrange
        String id = "1";
        Owner owner = new Owner();
        owner.setId(id);
        owner.setUsername("john");
        owner.setFirstName("John");
        owner.setLastName("Doe");
        owner.setChatId(12345L);

        when(ownerRepository.findById(id)).thenReturn(Optional.of(owner));

        // Act
        OwnerInfoResponseDto responseDto = ownerService.getInfoFromId(id);

        // Assert
        assertEquals(owner.getId(), responseDto.getId());
        assertEquals(owner.getUsername(), responseDto.getUsername());
        assertEquals(owner.getFirstName(), responseDto.getFirstName());
        assertEquals(owner.getLastName(), responseDto.getLastName());
        assertEquals(owner.getChatId(), responseDto.getChatId());
    }


}