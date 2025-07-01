package com.git.api.users.persistence.entities;

import jakarta.persistence.*;

import java.util.Calendar;

@Entity
@Table(name = "user", indexes = {
    @Index(name = "idx_user_user_id_user_email", columnList = "user_id, user_email")
})
public class User {
  @Id
  @Column(name = "user_id", length = 36, nullable = false)
  private String id;

  @Column(name = "user_name", length = 100, nullable = false)
  private String name;

  @Column(name = "user_email", length = 100, nullable = false, unique = true)
  private String email;

  @Column(name = "user_password", length = 100, nullable = false)
  private String password;

  @Column(name = "user_role", length = 100, nullable = false)
  private String role;

  @Column(name = "created_at")
  private Calendar createdAt;

  @Column(name = "updated_at")
  private Calendar updatedAt;

  @Version
  private Integer version;

  @PrePersist
  private void prePersist() {
    this.id = java.util.UUID.randomUUID().toString();
    this.createdAt = Calendar.getInstance();
    this.updatedAt = Calendar.getInstance();
    this.version = 1;
  }

  @PreUpdate
  private void preUpdate() {
    this.updatedAt = Calendar.getInstance();
    this.version++;
  }



}
