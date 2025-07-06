package com.git.api.users.services.impl;

import com.git.api.users.models.OrderCreateUserMessage;
import com.git.api.users.persistence.entities.User;
import com.git.api.users.persistence.repositories.UserRepository;
import com.git.api.users.services.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.List;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void processOrderNewUser(OrderCreateUserMessage message) {
        logger.info("Processing new user order: {}", message);
        User user = new User(
                message.getName(),
                message.getEmail(),
                message.getPassword(),
                message.getRole(),
                Calendar.getInstance(),
                Calendar.getInstance(),
                1
        );
        User save = userRepository.save(user);

        logger.info("User saved: {}", save);
    }

    @Override
    public User createUser(User user) {
        logger.info("Creating user: {} with REST API", user);
        return this.userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.listAll();
    }

    @Override
    public User findById(String id) {
        return this.userRepository.findById(id);
    }

    @Override
    public User updateUser(String id, User user) {
        User userSaved = this.findById(id);

        userSaved.setName(user.getName());
        userSaved.setEmail(user.getEmail());
        userSaved.setPassword(user.getPassword());
        userSaved.setRole(user.getRole());

        return this.userRepository.save(userSaved);
    }

    @Override
    public void deleteUser(String id) {
        this.userRepository.deleteById(Long.valueOf(id));
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
}
