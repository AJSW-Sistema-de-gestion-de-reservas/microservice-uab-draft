package com.example.microserviceuab.user;

import com.example.microserviceuab.common.ClientNotFoundException;
import com.example.microserviceuab.common.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImp implements ClientService {
    private final ClientRepository repository;

    @Autowired
    public ClientServiceImp(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public void register(ClientCreationRequestDto dto) {
        if (repository.existsByUsername(dto.getUsername()))
            throw new UsernameAlreadyExistsException("");

        Client client = Client.builder()
                .username(dto.getUsername())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .chatId(dto.getChatId())
                .build();

        repository.save(client);
    }

    @Override
    public ClientInfoResponseDto getInfoFromChatId(Long chatId) {
        Optional<Client> result = repository.findByChatId(chatId);

        if (result.isEmpty())
            throw new ClientNotFoundException();

        Client client = result.get();

        return ClientInfoResponseDto.builder()
                .id(client.getId())
                .username(client.getUsername())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .chatId(client.getChatId())
                .build();
    }

    @Override
    public ClientInfoResponseDto getInfoFromUsername(String username) {
        Optional<Client> result = repository.findByUsername(username);

        if (result.isEmpty())
            throw new ClientNotFoundException();

        Client client = result.get();

        return ClientInfoResponseDto.builder()
                .id(client.getId())
                .username(client.getUsername())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .chatId(client.getChatId())
                .build();
    }

    @Override
    public ClientInfoResponseDto getInfoFromId(String id) {
        Optional<Client> result = repository.findById(id);

        if (result.isEmpty())
            throw new ClientNotFoundException();

        Client client = result.get();

        return ClientInfoResponseDto.builder()
                .id(client.getId())
                .username(client.getUsername())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .chatId(client.getChatId())
                .build();
    }
}
