package com.codedifferently.lesson25.factory;

import com.codedifferently.lesson25.models.LibraryDataModel;
import com.codedifferently.lesson25.library.user.LibraryUserRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Loads the library data from the SQLite DB in resources/sqlite/library.db.
 * Matches LibraryDataLoader#loadData() returning models.LibraryDataModel.
 */
@Service
public class LibraryDbDataLoader implements LibraryDataLoader {

  @Override
  public LibraryDataModel loadData() throws IOException {
    // Locate sqlite/library.db on the classpath
    URL url = Thread.currentThread()
        .getContextClassLoader()
        .getResource("sqlite/library.db");
    if (url == null) {
      throw new IllegalStateException("sqlite/library.db not found on classpath");
    }

    try {
      // Put toURI() inside try so URISyntaxException is caught
      String dbPath = Paths.get(url.toURI()).toString();

      try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath)) {
        var dataModel = new LibraryDataModel();

        // TODO: Mirror your CSV/JSON loaders to populate these into dataModel:
        //   - mediaItems (dataModel.mediaItems or dataModel.setMediaItems(...))
        //   - guests (dataModel.guests or dataModel.setGuests(...))
        //   - checkoutsByEmail (dataModel.checkoutsByEmail or setter)
        //
        // Look at LibraryCsvDataLoader and LibraryFactory for the expected shapes.

        // Lesson 25: load users from DB
        var userRepo = new LibraryUserRepository(connection);
        dataModel.setUsers(userRepo.findAll());

        return dataModel;
      }
    } catch (Exception e) {
      // Wrap anything (SQL/URI) as IOException to satisfy the interface
      throw new IOException("Failed loading DB data", e);
    }
  }
}
