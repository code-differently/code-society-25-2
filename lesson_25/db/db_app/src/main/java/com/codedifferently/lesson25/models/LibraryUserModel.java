package com.codedifferently.lesson25.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "library_users")
public class LibraryUserModel {

  @Id public UUID id;
  public String email;

  @Column(name = "first_name")
  public String firstName;

  @Column(name = "last_name")
  public String lastName;

  @Column(name = "password_hash")
  public String passwordHash;
}
