package com.codedifferently.lesson25.repository;

import com.codedifferently.lesson25.models.LibraryGuestModel;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface LibraryGuestRepository extends CrudRepository<LibraryGuestModel, String> {

  @Override
  List<LibraryGuestModel> findAll();
}
