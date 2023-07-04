package com.example.microserviceuab.accommodation;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ExistsQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends MongoRepository<Room, String> {
    @Query("{ id: ?0, accommodation: ?1 }")
    Optional<Room> findByIdAndAccommodation(String id, ObjectId accommodation);

    @Query("{ accommodation: ?0 }")
    List<Room> findAllByAccommodation(ObjectId accommodationId);

    @ExistsQuery("{ name:  ?0 }")
    boolean existsByName(String name);
}
