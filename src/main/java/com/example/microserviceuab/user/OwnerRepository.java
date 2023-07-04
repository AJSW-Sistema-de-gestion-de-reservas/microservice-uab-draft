package com.example.microserviceuab.user;

import org.springframework.data.mongodb.repository.ExistsQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends MongoRepository<Owner, String> {
    Optional<Owner> findByUsername(String username);

    Optional<Owner> findByChatId(Long chatId);

    @ExistsQuery("{ 'username':  ?0 }")
    boolean existsByUsername(String username);
}
