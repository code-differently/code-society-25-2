package com.codedifferently.lesson25.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.codedifferently.lesson25.models.LibraryUserModel;

public interface LibraryUserRepository extends CrudRepository<LibraryUserModel, UUID> {

  @Override
  List<LibraryUserModel> findAll();
}
