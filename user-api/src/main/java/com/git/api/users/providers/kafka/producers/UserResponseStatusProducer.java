package com.git.api.users.providers.kafka.producers;

import com.git.api.users.models.OrderCreateUserMessage;
import com.git.api.users.providers.kafka.ProducerService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class UserResponseStatusProducer implements ProducerService {

    private final Logger logger = LoggerFactory.getLogger(UserResponseStatusProducer.class);

    @Inject
    @Channel("order-user-create")
    Emitter<OrderCreateUserMessage> emitter;

    @Override
    public <T> Uni<Void> send(T message) {
        logger.info("Start of send message to Kafka topic order-user-create");
        emitter.send(Message.of((OrderCreateUserMessage) message)
                .withAck(() -> {
                    logger.info("Message sent to Kafka topic order-user-create");
                    return CompletableFuture.completedFuture(null);
                })
                .withNack(throwable -> {
                    logger.error("Message not sent to Kafka topic order-user-create");
                    return CompletableFuture.completedFuture(null);
                })

        );
        return Uni.createFrom().voidItem();
    }
}
