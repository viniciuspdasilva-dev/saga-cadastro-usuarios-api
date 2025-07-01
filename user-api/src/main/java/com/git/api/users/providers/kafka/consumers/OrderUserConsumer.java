package com.git.api.users.providers.kafka.consumers;

import com.git.api.users.models.OrderCreateUserMessage;
import com.git.api.users.services.UserService;
import io.smallrye.reactive.messaging.kafka.api.IncomingKafkaRecordMetadata;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class OrderUserConsumer {

    private final Logger logger = LoggerFactory.getLogger(OrderUserConsumer.class);

    private final UserService userService;

    public OrderUserConsumer(UserService userService) {
        this.userService = userService;
    }

    @Incoming("order-user-create")
    public CompletionStage<Void> receiveOrderCreateNewUser(Message<OrderCreateUserMessage> message) {
        IncomingKafkaRecordMetadata metadata = message.getMetadata(IncomingKafkaRecordMetadata.class)
                .orElseThrow();

        OrderCreateUserMessage payload = message.getPayload();

        logger.info("%s - Got a order to create a new user with payload: %s", metadata.getTopic(), payload);

        userService.processOrderNewUser(payload);

        return message.ack();
    }
}
