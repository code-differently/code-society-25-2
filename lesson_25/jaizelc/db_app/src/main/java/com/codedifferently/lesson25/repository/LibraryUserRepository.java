package com.codedifferently.lesson25.repository;

import com.codedifferently.lesson25.models.LibraryUserModel;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface LibraryUserRepository extends CrudRepository<LibraryUserModel, String> {
  @Override
  List<LibraryUserModel> findAll();
}
