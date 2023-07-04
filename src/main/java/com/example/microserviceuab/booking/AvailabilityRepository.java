package com.example.microserviceuab.booking;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ExistsQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface AvailabilityRepository extends MongoRepository<Availability, String> {
    @Query("{ accommodation: ?0, room: ?1, date: { $eq: ?2 } }")
    Optional<Availability> findByAccommodationAndRoomAndDate(ObjectId accommodationId, ObjectId roomId, Instant date);

    @Query("{ accommodation: ?0, room: ?1 }")
    List<Availability> findAllByAccommodationAndRoom(ObjectId accommodationId, ObjectId roomId);

    @Query("{ accommodation: ?0, room: ?1, date: { $gte: ?2, $lte: ?3 } }")
    List<Availability> findAllByAccommodationAndRoomAndDateBetween(ObjectId accommodation, ObjectId room,
                                                                   Instant fromDate, Instant toDate);

    @Query("{ accommodation: ?0 }")
    List<Availability> findAllByAccommodation(ObjectId accommodation);

    @Query("{ accommodation:  ?0, date: { $eq: ?1 } }")
    List<Availability> findAllByAccommodationAndDate(ObjectId accommodation, Instant date);

    @Query("{ accommodation:  ?0, date: { $gte: ?1, $lte: ?2 } }")
    List<Availability> findAllByAccommodationAndDateBetween(ObjectId accommodation, Instant fromDate, Instant toDate);

    @ExistsQuery("{ accommodation: ?0, room: ?1, date: { $gte: ?2, $lte: ?2 } }")
    boolean existsByAccommodationIdRoomIdAndDate(ObjectId accommodationId, ObjectId roomId, Instant date);
}
