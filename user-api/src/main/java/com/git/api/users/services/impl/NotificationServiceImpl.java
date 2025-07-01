package com.git.api.users.services.impl;

import com.git.api.users.providers.kafka.ProducerService;
import com.git.api.users.services.NotificationService;
import com.git.api.users.services.UserService;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.spi.Producer;

@ApplicationScoped
public class NotificationServiceImpl implements NotificationService {
    @Override
    public <T> void sendNotification(T message, ProducerService service) {
        Uni.createFrom().
            item(message)
                .emitOn(Infrastructure.getDefaultExecutor())
                .subscribe()
                .with(service::send, Throwable::getMessage);
    }
}
