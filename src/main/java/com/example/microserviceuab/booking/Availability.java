package com.example.microserviceuab.booking;

import com.example.microserviceuab.accommodation.Accommodation;
import com.example.microserviceuab.accommodation.Room;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Builder
@Data
@Document(collection = "availability")
@CompoundIndex(def = "{ accommodation: -1, room: -1, date: -1 }")
public class Availability {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private Date date;
    private int availableQuantity;

    @DocumentReference(lazy = true)
    private Accommodation accommodation;

    @DocumentReference(lazy = true)
    private Room room;
}
