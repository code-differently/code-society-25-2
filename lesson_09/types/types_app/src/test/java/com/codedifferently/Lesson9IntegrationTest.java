package com.codedifferently.lesson9;

import com.codedifferently.lesson9.dataprovider.DataProvider;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Lesson9IntegrationTest {

    @Autowired
    private Lesson9 lesson9; // Spring injects your application

    @Autowired
    private List<DataProvider> providers;

    private static final String DATA_PATH = Lesson9.getDataPath();

    @BeforeEach
    void cleanDataDir() throws Exception {
        File dir = new File(DATA_PATH);
        if (dir.exists()) {
            Files.walk(dir.toPath())
                    .map(java.nio.file.Path::toFile)
                    .sorted((a, b) -> -a.compareTo(b)) // delete children first
                    .forEach(File::delete);
        }
        dir.mkdirs();
    }

    @Test
    void run_withSingleProvider_createsFileInCorrectPath() throws Exception {
        // Arrange
        String providerName = providers.get(0).getProviderName();
        File expectedFile = new File(DATA_PATH, providerName + ".json");

        // Act
        lesson9.run(providerName);

        // Assert
        assertThat(expectedFile)
                .exists()
                .isFile();
    }

    @Test
    void run_withBulkOption_createsAllProviderFiles() throws Exception {
        // Act
        lesson9.run("--bulk");

        // Assert: every provider should have a file in the data path
        for (DataProvider provider : providers) {
            File file = new File(DATA_PATH, provider.getProviderName() + ".json");
            assertThat(file)
                    .as("File for provider " + provider.getProviderName() + " should exist")
                    .exists()
                    .isFile();
        }
    }
}
