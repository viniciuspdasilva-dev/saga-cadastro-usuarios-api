package com.git.api.users.services;

import com.git.api.users.providers.kafka.ProducerService;

public interface NotificationService {
    <T> void sendNotification(T message, ProducerService service);
}
