package com.git.api.users.persistence.entities;

import jakarta.persistence.*;

import java.util.Calendar;

@Entity
@Table(name = "client_user", indexes = {
    @Index(name = "idx_clientId_email", columnList = "client_user_id, user_email")
})
public class User {
  @Id
  @Column(name = "client_user_id", length = 36, nullable = false)
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

  public User() {
  }

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

  public String getId() {
    return id;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public Calendar getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Calendar updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Calendar getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Calendar createdAt) {
    this.createdAt = createdAt;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public User(String name, String email, String password, String role) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.role = role;
  }

  public User(String name, String email, String password, String role, Calendar createdAt, Calendar updatedAt, Integer version) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.role = role;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.version = version;
  }
}
