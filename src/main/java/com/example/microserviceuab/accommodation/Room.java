package com.example.microserviceuab.accommodation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "room")
public class Room {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String name;
    private double price;
    private int maxPeople;
    private int quantity;
    private boolean enabled;

    @DocumentReference(lazy = true)
    private Accommodation accommodation;
}
