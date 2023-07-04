package com.example.microserviceuab.user;

import org.springframework.data.mongodb.repository.ExistsQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {
    Optional<Client> findByUsername(String username);

    Optional<Client> findByChatId(Long chatId);

    @ExistsQuery("{ 'username':  ?0 }")
    boolean existsByUsername(String username);
}