package com.git.api.users.services.impl;

import com.git.api.users.models.OrderCreateUserMessage;
import com.git.api.users.services.UserService;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserServiceImpl implements UserService {
    @Override
    public void processOrderNewUser(OrderCreateUserMessage message) {

    }
}
