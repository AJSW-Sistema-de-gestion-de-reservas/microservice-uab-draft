package com.example.microserviceuab.service;

import com.example.microserviceuab.user.Client;
import com.example.microserviceuab.user.ClientInfoResponseDto;
import com.example.microserviceuab.user.ClientRepository;
import com.example.microserviceuab.user.ClientServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceImpTest {

    @Mock
    private ClientRepository repository;

    @InjectMocks
    private ClientServiceImp clientService;

    @Test
    public void testGetInfoFromChatId_ValidChatId_ReturnsClientInfoResponseDto() {

        long chatId = 12345;
        Client client = new Client();
        client.setId("1");
        client.setUsername("johnDoe");
        client.setFirstName("John");
        client.setLastName("Doe");
        client.setChatId(chatId);
        Optional<Client> result = Optional.of(client);

        when(repository.findByChatId(chatId)).thenReturn(result);

        ClientInfoResponseDto responseDto = clientService.getInfoFromChatId(chatId);

        assertEquals(client.getId(), responseDto.getId());
        assertEquals(client.getUsername(), responseDto.getUsername());
        assertEquals(client.getFirstName(), responseDto.getFirstName());
        assertEquals(client.getLastName(), responseDto.getLastName());
        assertEquals(client.getChatId(), responseDto.getChatId());
    }

    @Test
    public void testGetInfoFromUsername_ValidUsername_ReturnsClientInfoResponseDto() {

        String username = "johnDoe";
        Client client = new Client();
        client.setId("1");
        client.setUsername(username);
        client.setFirstName("John");
        client.setLastName("Doe");
        client.setChatId(12345);
        Optional<Client> result = Optional.of(client);

        when(repository.findByUsername(username)).thenReturn(result);

        ClientInfoResponseDto responseDto = clientService.getInfoFromUsername(username);

        assertEquals(client.getId(), responseDto.getId());
        assertEquals(client.getUsername(), responseDto.getUsername());
        assertEquals(client.getFirstName(), responseDto.getFirstName());
        assertEquals(client.getLastName(), responseDto.getLastName());
        assertEquals(client.getChatId(), responseDto.getChatId());
    }

    @Test
    public void testGetInfoFromId_ValidId_ReturnsClientInfoResponseDto() {

        String id = "1";
        Client client = new Client();
        client.setId(id);
        client.setUsername("johnDoe");
        client.setFirstName("John");
        client.setLastName("Doe");
        client.setChatId(12345);
        Optional<Client> result = Optional.of(client);

        when(repository.findById(id)).thenReturn(result);

        ClientInfoResponseDto responseDto = clientService.getInfoFromId(id);

        assertEquals(client.getId(), responseDto.getId());
        assertEquals(client.getUsername(), responseDto.getUsername());
        assertEquals(client.getFirstName(), responseDto.getFirstName());
        assertEquals(client.getLastName(), responseDto.getLastName());
        assertEquals(client.getChatId(), responseDto.getChatId());
    }

}