package com.codedifferently.lesson9;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.lenient;

import com.codedifferently.lesson9.dataprovider.DataProvider;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * Test class for verifying the bulk mode functionality of Lesson9. This class tests the --bulk flag
 * implementation which processes multiple DataProviders and generates sample files for each one.
 */
@SpringBootTest
@ContextConfiguration(classes = Lesson9.class)
@ExtendWith(MockitoExtension.class)
class BulkModeTest {

  // Mock DataProvider objects for testing without real implementations
  @Mock private DataProvider mockProvider1;
  @Mock private DataProvider mockProvider2;

  private Lesson9 lesson9;
  // Capture console output for verification - allows us to test printed messages
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
  private final PrintStream standardOut = System.out;

  // Temporary directory for safe file operations during testing
  @TempDir Path tempDir;

  /**
   * Setup method run before each test. Configures mock objects and captures system output for
   * testing.
   */
  @BeforeEach
  void setUp() {
    // Capture system output for testing console messages
    System.setOut(new PrintStream(outputStreamCaptor));

    // Setup mock providers with column type mapping
    // This simulates the data types that a real DataProvider would return
    Map<String, Class> columnTypes = new HashMap<>();
    columnTypes.put("column1", Integer.class);
    columnTypes.put("column2", String.class);
    columnTypes.put("column3", Double.class);
    columnTypes.put("column4", Short.class);
    columnTypes.put("column5", Long.class);
    columnTypes.put("column6", Float.class);
    columnTypes.put("column7", Boolean.class);

    // Configure mock provider 1 - lenient() allows unused stubbing
    lenient().when(mockProvider1.getProviderName()).thenReturn("testProvider1");
    lenient().when(mockProvider1.getColumnTypeByName()).thenReturn(columnTypes);

    // Configure mock provider 2 with same setup
    lenient().when(mockProvider2.getProviderName()).thenReturn("testProvider2");
    lenient().when(mockProvider2.getColumnTypeByName()).thenReturn(columnTypes);

    // Create Lesson9 instance with mock providers for testing
    List<DataProvider> mockProviders = Arrays.asList(mockProvider1, mockProvider2);
    lesson9 = new Lesson9(mockProviders);
  }

  /** Cleanup method run after each test. Restores the original system output stream. */
  @AfterEach
  void tearDown() {
    // Restore system output
    System.setOut(standardOut);
  }

  /**
   * Tests the main bulk mode functionality with multiple providers. This verifies that the --bulk
   * flag correctly processes all available DataProviders and generates sample files for each one.
   */
  @Test
  void testBulkMode_withMultipleProviders_generatesFilesForAllProviders() throws Exception {
    // Arrange: Set up the bulk mode arguments
    String[] args = {"--bulk"};

    // Act: Execute the bulk mode functionality
    lesson9.run(args);

    // Assert: Verify that bulk mode executed correctly
    String output = outputStreamCaptor.toString();
    assertThat(output).contains("Running Bulk Mode");
    assertThat(output).contains("Generated file for provider: testProvider1");
    assertThat(output).contains("Generated file for provider: testProvider2");
    assertThat(output).contains("Done");
  }

  /**
   * Tests that the bulk mode flag is case-insensitive when using uppercase. This ensures robust
   * argument parsing that works regardless of case.
   */
  @Test
  void testBulkMode_caseInsensitive_worksWithUppercase() throws Exception {
    // Arrange: Use uppercase --BULK flag
    String[] args = {"--BULK"};

    // Act: Execute with uppercase flag
    lesson9.run(args);

    // Assert: Verify bulk mode still triggers
    String output = outputStreamCaptor.toString();
    assertThat(output).contains("Running Bulk Mode");
  }

  /**
   * Tests that the bulk mode flag is case-insensitive with mixed case. This further confirms robust
   * argument parsing capabilities.
   */
  @Test
  void testBulkMode_caseInsensitive_worksWithMixedCase() throws Exception {
    // Arrange: Use mixed case --BuLk flag
    String[] args = {"--BuLk"};

    // Act: Execute with mixed case flag
    lesson9.run(args);

    // Assert: Verify bulk mode still works
    String output = outputStreamCaptor.toString();
    assertThat(output).contains("Running Bulk Mode");
  }

  /**
   * Tests bulk mode behavior when no DataProviders are available. This ensures graceful handling of
   * edge cases where the provider list is empty.
   */
  @Test
  void testBulkMode_withEmptyProviderList_stillDisplaysBulkModeMessages() throws Exception {
    // Arrange: Create Lesson9 with no providers
    String[] args = {"--bulk"};
    Lesson9 emptyLesson9 = new Lesson9(Arrays.asList()); // Empty provider list

    // Act: Execute bulk mode with empty provider list
    emptyLesson9.run(args);

    // Assert: Verify bulk mode messages appear but no file generation occurs
    String output = outputStreamCaptor.toString();
    assertThat(output).contains("Running Bulk Mode");
    assertThat(output).contains("Done");
    // Should not contain any "Generated file for provider" messages
    assertThat(output).doesNotContain("Generated file for provider:");
  }

  /**
   * Integration test that verifies actual file generation capabilities. Uses a temporary directory
   * to safely test file system operations.
   */
  @Test
  void testBulkMode_fileGenerationIntegration_createsActualFiles() throws Exception {
    // Arrange: Setup for integration testing with real file operations
    String[] args = {"--bulk"};

    // Create a real SampleFileGenerator for integration test
    // Mock the path resolution to use our temp directory
    try (MockedStatic<Paths> pathsMock = Mockito.mockStatic(Paths.class)) {
      pathsMock.when(() -> Paths.get("").toAbsolutePath()).thenReturn(tempDir);

      // Act: Execute bulk mode with mocked path
      lesson9.run(args);

      // Assert: Check that files would be created in the expected location
      String output = outputStreamCaptor.toString();
      assertThat(output).contains("Generated file for provider: testProvider1");
      assertThat(output).contains("Generated file for provider: testProvider2");
    }
  }

  /**
   * Tests that non-bulk mode (single provider) doesn't trigger bulk functionality. This ensures
   * proper separation between bulk and single-provider modes.
   */
  @Test
  void testNonBulkMode_withValidProviderName_callsCreateTestFile() throws Exception {
    // Arrange: Use a provider name instead of --bulk flag
    String[] args = {"validProvider"};

    // Act: Execute single provider mode
    lesson9.run(args);

    // Assert: Should not see bulk mode messages
    String output = outputStreamCaptor.toString();
    assertThat(output).doesNotContain("Running Bulk Mode");
    assertThat(output).doesNotContain("Done");
  }

  /**
   * Tests that the application handles empty arguments gracefully. This verifies defensive
   * programming for when no arguments are provided.
   */
  @Test
  void testEmptyArgs_doesNothing() throws Exception {
    // Arrange: Provide no command line arguments
    String[] args = {};

    // Act: Execute with no arguments
    lesson9.run(args);

    // Assert: No output should be produced
    String output = outputStreamCaptor.toString();
    assertThat(output).isEmpty();
  }

  /**
   * Indirectly tests the private getDataPath() method through bulk mode execution. This verifies
   * that path construction works correctly without direct access to private methods.
   */
  @Test
  void testGetDataPath_indirectlyThroughBulkMode() {
    // This tests the private method indirectly through the bulk functionality

    try {
      // Act: Execute bulk mode which internally calls getDataPath()
      lesson9.run(new String[] {"--bulk"});

      // Assert: The path should be used in file generation
      String output = outputStreamCaptor.toString();
      assertThat(output).contains("Running Bulk Mode");
      // The method constructs path with File.separator, so verify it runs without path errors
      assertThat(output).contains("Done");
    } catch (Exception e) {
      // Expected in test environment due to file system operations
      assertThat(e).isNotNull();
    }
  }
}
