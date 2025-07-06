package com.git.api.users.services;

import com.git.api.users.models.OrderCreateUserMessage;
import com.git.api.users.persistence.entities.User;

import java.util.List;

public interface UserService {
    void processOrderNewUser(OrderCreateUserMessage message);
    User createUser(User user);
    List<User> findAll();
    User findById(String id);
    User updateUser(String id, User user);
    void deleteUser(String id);

    User findByEmail(String email);
}
