package com.example.microserviceuab.booking;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {
    @Query("{ accommodation: ?0 }")
    List<Booking> findAllByAccommodation(ObjectId accommodationId);

    @Query("{ accommodation: ?0, checkIn: { $lte: ?1 }, checkOut: { $gte: ?1 } }")
    List<Booking> findAllByAccommodationAndDate(ObjectId accommodation, Instant date);

    @Query("{ accommodation: ?0, $or: [" +
            "    { checkIn: { $lte: ?1 }, checkOut: { $gte: ?1, $lte: ?2 } }," + // Reservas que ya comenzaron y siguen en curso
            "    { checkIn: { $gte: ?1, $lte: ?2 } }," +  // Reservas que inician dentro del período
            "    { checkIn: { $lte: ?1, $gte: ?2 }, checkOut: { $gte: ?2 } }" + // Reservas que continúan después de que termine
            "  ]}")
    List<Booking> findAllByAccommodationAndDateBetween(ObjectId accommodation, Instant fromDate, Instant toDate);

    @Query("{ accommodation: ?0, room: ?1 }")
    List<Booking> findAllByAccommodationAndRoom(ObjectId accommodation, ObjectId room);

    @Query("{ accommodation: ?0, room: ?1, checkIn: { $lte: ?2 }, checkOut: { $gte: ?2 } }")
    List<Booking> findAllByAccommodationAndRoomAndDate(ObjectId accommodation, ObjectId room, Instant date);

    @Query("{ accommodation: ?0, room: ?1, $or: [" +
            "    { checkIn: { $lte: ?2 }, checkOut: { $gte: ?2, $lte: ?3 } }," + // Reservas que ya comenzaron y siguen en curso
            "    { checkIn: { $gte: ?2, $lte: ?3 } }," +  // Reservas que inician dentro del período
            "    { checkIn: { $lte: ?2, $gte: ?3 }, checkOut: { $gte: ?3 } }" + // Reservas que continúan después de que termine
            "  ]}")
    List<Booking> findAllByAccommodationAndRoomAndDateBetween(ObjectId accommodation, ObjectId room, Instant fromDate,
                                                              Instant toDate);

    @Query("{ client: ?0 }")
    List<Booking> findAllByClient(ObjectId client);
}
