package com.codedifferently.lesson25.factory;

import com.codedifferently.lesson25.models.LibraryDataModel;
import com.codedifferently.lesson25.repository.LibraryGuestRepository;
import com.codedifferently.lesson25.repository.LibraryUserRepository;
import com.codedifferently.lesson25.repository.MediaItemRepository;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class LibraryDbDataLoader implements LibraryDataLoader {

  @Autowired private MediaItemRepository mediaItemsRepository;
  @Autowired private LibraryGuestRepository libraryGuestRepository;
  @Autowired private LibraryUserRepository libraryUserRepository;

  @Override
  public LibraryDataModel loadData() throws IOException {
    var model = new LibraryDataModel();

    model.mediaItems = mediaItemsRepository.findAll();
    model.guests = libraryGuestRepository.findAll();
    model.users = libraryUserRepository.findAll();
    return model;
  }
}
