package com.git.api.users.services;

import com.git.api.users.models.OrderCreateUserMessage;

public interface UserService {
    void processOrderNewUser(OrderCreateUserMessage message);
}
