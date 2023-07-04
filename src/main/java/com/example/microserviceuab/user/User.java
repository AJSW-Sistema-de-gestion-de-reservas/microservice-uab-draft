package com.example.microserviceuab.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@SuperBuilder
@Data
@NoArgsConstructor
@Document
public class User {
    @MongoId(FieldType.OBJECT_ID)
    protected String id;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    protected String username;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    protected long chatId;
    protected String firstName;
    protected String lastName;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
