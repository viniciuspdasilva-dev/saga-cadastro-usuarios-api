package com.git.api.users.persistence.repositories;

import com.git.api.users.persistence.entities.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public User findByEmail(String email) {
        return find("user_email = :email", Parameters.with("email", email))
                .firstResultOptional()
                .orElseThrow(NotFoundException::new);
    }

    public User findById(String id) {
        return find("client_user_id = :id", Parameters.with("id", id)).firstResult();
    }

    @Transactional
    public User save(User user) {
        this.persistAndFlush(user);

        return user;
    }
}
