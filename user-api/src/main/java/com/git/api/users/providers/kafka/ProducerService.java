package com.git.api.users.providers.kafka;

import io.smallrye.mutiny.Uni;

public interface ProducerService {

    <T>Uni<Void> send(T message);
}
