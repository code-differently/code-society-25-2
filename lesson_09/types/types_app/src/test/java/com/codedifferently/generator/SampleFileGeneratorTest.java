package com.codedifferently.generator;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.codedifferently.lesson9.Lesson9;
import com.codedifferently.lesson9.dataprovider.DataProvider;
import com.codedifferently.lesson9.generator.SampleFileGenerator;
import java.io.File;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = Lesson9.class)
class SampleFileGeneratorTest {

  private final SampleFileGenerator generator = new SampleFileGenerator();
  private final String outputDir = "build/test-output";

  @AfterEach
  void cleanup() throws Exception {
    File dir = new File(outputDir);
    if (dir.exists()) {
      for (File f : dir.listFiles()) {
        f.delete();
      }
      dir.delete();
    }
  }

  // --- Stub Provider ---
  static class StubProvider extends DataProvider {
    private final Map<String, Class<?>> schema;

    StubProvider(Map<String, Class<?>> schema) {
      this.schema = schema;
    }

    @Override
    public Map getColumnTypeByName() {
      return schema;
    }

    @Override
    public String getProviderName() {
      throw new UnsupportedOperationException("Unimplemented method 'getProviderName'");
    }
  }

  @Test
  void testCreateTestFileSingleMode() throws Exception {
    Map<String, Class<?>> schema = new LinkedHashMap<>();
    schema.put("col1", String.class);
    schema.put("col2", Integer.class);

    DataProvider provider = new StubProvider(schema);

    generator.createTestFile(outputDir, provider, "single");

    File outputFile = new File(outputDir, "stub.json");
    assertTrue(outputFile.exists());
    String content = Files.readString(outputFile.toPath());
    assertTrue(content.contains("col1"));
    assertTrue(content.contains("col2"));
  }

  @Test
  void testCreateTestFileBulkMode() throws Exception {
    Map<String, Class<?>> schema = new LinkedHashMap<>();
    schema.put("id", Integer.class);
    schema.put("name", String.class);

    DataProvider provider = new StubProvider(schema);

    generator.createTestFile(outputDir, provider, "bulk");

    File outputFile = new File(outputDir, "stub.json");
    assertTrue(outputFile.exists());
    String content = Files.readString(outputFile.toPath());
    assertTrue(content.contains("id"));
    assertTrue(content.contains("name"));
  }

  @Test
  void testCreateTestFileInvalidMode() {
    Map<String, Class<?>> schema = new LinkedHashMap<>();
    schema.put("x", String.class);
    DataProvider provider = new StubProvider(schema);

    assertThrows(
        IllegalArgumentException.class,
        () -> generator.createTestFile(outputDir, provider, "invalidMode"));
  }

  @Test
  void testCreateTestFileBulkModeWithUnsupportedType() {
    Map<String, Class<?>> schema = new LinkedHashMap<>();
    schema.put("badColumn", Object.class); // unsupported type
    DataProvider provider = new StubProvider(schema);

    assertThrows(
        IllegalArgumentException.class,
        () -> generator.createTestFile(outputDir, provider, "bulk"));
  }
}
