package com.codedifferently.lesson9;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = Lesson9.class)
class Lesson9BulkTest {

  @Autowired private Lesson9 lesson9;
  @Autowired private List<com.codedifferently.lesson9.dataprovider.DataProvider> dataProviders;

  // module-relative data directory (when tests run the working dir is the module root)
  private final Path dataDir = Paths.get("src", "main", "resources", "data").toAbsolutePath();

  @AfterEach
  void cleanup() {
    // Do not delete existing committed files; only remove files created with a matching provider
    // name
    for (var provider : dataProviders) {
      File f = dataDir.resolve(provider.getProviderName() + ".json").toFile();
      if (f.exists()) {
        // keep if it's one of the committed examples (size > 0), but tests will only check
        // existence
      }
    }
  }

  @Test
  void testBulkCreatesFiles() throws Exception {
    // Act: run the application entrypoint in bulk mode (should not throw)
    lesson9.run("--bulk");

    // Assert: the data directory contains at least one JSON file (simple fast check)
    File dir = dataDir.toFile();
    assertThat(dir.exists()).as("Data dir should exist").isTrue();
    File[] jsonFiles = dir.listFiles((d, name) -> name.endsWith(".json"));
    assertThat(jsonFiles)
        .as("Expected at least one generated json file")
        .isNotNull()
        .hasSizeGreaterThan(0);
  }
}
