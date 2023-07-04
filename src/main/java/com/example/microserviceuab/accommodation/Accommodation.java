package com.example.microserviceuab.accommodation;

import com.example.microserviceuab.user.Owner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "accommodation")
public class Accommodation {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    @TextIndexed
    private String name;
    private String address;
    @TextIndexed
    private String city;
    @TextIndexed
    private String province;
    private String postalCode;
    private boolean enabled;

    @DocumentReference(lazy = true)
    private Owner owner;

    @DocumentReference(lazy = true)
    private List<Room> rooms;
}
