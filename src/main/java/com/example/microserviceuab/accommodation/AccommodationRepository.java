package com.example.microserviceuab.accommodation;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccommodationRepository extends MongoRepository<Accommodation, String> {
    @Query("{ name:  ?0, owner: ?1 }")
    Optional<Accommodation> findByNameAndOwner(String name, ObjectId owner);

    @Query("{ name: {'$regex' : ?0, '$options' : 'i'}, enabled: true }")
    List<Accommodation> findAllByName(String name);

    @Query("{ owner: ?0 }")
    List<Accommodation> findAllByOwnerId(ObjectId ownerId);

    @Query("{ city:  {'$regex' : ?0, '$options' : 'i'}, enabled: true }")
    List<Accommodation> findAllByCity(String city);

    @Query("{ province: {'$regex' : ?0, '$options' : 'i'}, enabled: true }")
    List<Accommodation> findAllByProvince(String province);

    @Query("{$and: [ { $or : [ { $where: '\"?0\".length == 0' } , { name: {'$regex' : ?0, '$options' : 'ix'} } ] },"
            + "{ $or : [ { $where: '\"?1\".length == 0' } , { city: {'$regex' : ?1, '$options' : 'ix'} } ] },"
            + "{ $or : [ { $where: '\"?2\".length == 0' } , { province: {'$regex' : ?2, '$options' : 'ix'} } ] },"
            + "{ enabled: true } ] }")
    List<Accommodation> findAllByNameCityAndProvince(String name, String city, String province);

    List<Accommodation> findAllBy(TextCriteria criteria);
}
