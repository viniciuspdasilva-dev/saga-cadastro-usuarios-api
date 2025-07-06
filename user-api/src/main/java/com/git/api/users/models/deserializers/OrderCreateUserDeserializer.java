package com.git.api.users.models.deserializers;

import com.git.api.users.persistence.entities.User;
import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class OrderCreateUserDeserializer extends JsonbDeserializer<User> {
    public OrderCreateUserDeserializer() {
        super(User.class);
    }
}
