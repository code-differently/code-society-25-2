package com.codedifferently.lesson9.generator;

// ENHANCEMENT: Added DataProvider import to support provider-aware generation
import com.codedifferently.lesson9.dataprovider.DataProvider;
import com.codedifferently.lesson9.generator.Generators.*;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A class to generate sample files with random data.
 *
 * <p>ENHANCEMENT SUMMARY: - Added provider-aware generation capability for test compatibility -
 * Fixed critical bugs in row generation logic - Modernized code with Java 14+ switch expressions -
 * Improved type safety with proper generic usage
 *
 * <p>CHANGES MADE: 1. NEW FEATURE: Provider-aware generation via createTestFileForProvider() 2. BUG
 * FIX: Fixed double row generation in createSampleData() 3. BUG FIX: Fixed ignored parameter in
 * createRow() method 4. ENHANCEMENT: Modern switch expressions in generateValueForType() 5. TYPE
 * SAFETY: Added Class<?> wildcards for better generics 6. BACKWARD COMPATIBILITY: All original
 * methods preserved
 */
public class SampleFileGenerator {

  private static final ValueGenerator[] GENERATORS = {
    new IntValueGenerator(),
    new StringValueGenerator(),
    new DoubleValueGenerator(),
    new ShortValueGenerator(),
    new LongValueGenerator(),
    new FloatValueGenerator(),
    new BooleanValueGenerator()
  };

  /**
   * ORIGINAL METHOD: Create a test file with random sample data. This method uses shuffled
   * generators to create random data types for each column. Preserved for backward compatibility.
   *
   * @param path the path to the directory where the file will be created
   * @param providerName the name of the provider
   */
  public void createTestFile(String path, String providerName) {
    var generators = getShuffledGenerators();
    ArrayList<Map<String, String>> rows = createSampleData(generators);
    saveToJsonFile(path, providerName, rows);
  }

  /**
   * NEW METHOD: Create a test file with sample data that matches the DataProvider's expected types.
   * This is the enhanced version that ensures test compatibility by generating data according to
   * the provider's type specifications.
   *
   * @param path the path to the directory where the file will be created
   * @param provider the DataProvider to generate data for
   */
  public void createTestFileForProvider(String path, DataProvider provider) {
    String providerName = provider.getProviderName();
    ArrayList<Map<String, String>> rows = createSampleDataForProvider(provider);
    saveToJsonFile(path, providerName, rows);
  }

  private List<ValueGenerator> getShuffledGenerators() {
    var generators = Arrays.asList(GENERATORS);
    Collections.shuffle(generators);
    return generators;
  }

  // BUG FIX: Fixed the double-generation bug in this method
  // BEFORE: Created row twice per iteration (unused variable + actual add)
  // AFTER: Clean single row generation per iteration
  private ArrayList<Map<String, String>> createSampleData(List<ValueGenerator> generators) {
    var rows = new ArrayList<Map<String, String>>();
    for (var i = 0; i < 10; ++i) {
      rows.add(createRow(generators)); // Fixed: only create row once
    }
    return rows;
  }

  /**
   * NEW METHOD: Creates sample data that matches a specific DataProvider's type requirements. This
   * ensures that generated files will pass the DataProvider's tests by creating data with the exact
   * types expected by each column.
   */
  private ArrayList<Map<String, String>> createSampleDataForProvider(DataProvider provider) {
    var rows = new ArrayList<Map<String, String>>();
    for (var i = 0; i < 10; ++i) {
      rows.add(createRowForProvider(provider));
    }
    return rows;
  }

  // BUG FIX: Fixed the ignored parameter bug in this method
  // BEFORE: Ignored 'generators' parameter and used static GENERATORS array
  // AFTER: Properly uses the passed-in generators parameter
  private Map<String, String> createRow(List<ValueGenerator> generators) {
    var row = new LinkedHashMap<String, String>();
    for (int i = 0; i < generators.size(); ++i) { // Fixed: use parameter size
      var columnIndex = i + 1;
      row.put("column" + columnIndex, generators.get(i).generateValue()); // Fixed: use parameter
    }
    return row;
  }

  /**
   * NEW METHOD: Creates a single row with data types that match the DataProvider's specifications.
   * This method interrogates the provider's getColumnTypeByName() mapping and generates values of
   * the correct type for each column, ensuring test compatibility.
   */
  private Map<String, String> createRowForProvider(DataProvider provider) {
    var row = new LinkedHashMap<String, String>();
    var columnTypes = provider.getColumnTypeByName(); // Get provider's type expectations

    for (int i = 1; i <= 7; ++i) { // Generate 7 columns as expected
      String columnName = "column" + i;
      Class<?> expectedType = columnTypes.get(columnName); // Look up expected type
      String value = generateValueForType(expectedType); // Generate value of correct type
      row.put(columnName, value);
    }
    return row;
  }

  /**
   * NEW METHOD: Generates a value of a specific Java type using modern switch expressions.
   * ENHANCEMENT: Refactored from if-else chain to switch expression for better readability. Uses
   * Class<?> wildcard for type safety and supports all DataProvider data types.
   */
  private String generateValueForType(Class<?> type) { // Wildcard - best practice
    return switch (type.getSimpleName()) {
      case "Integer" -> new IntValueGenerator().generateValue();
      case "String" -> new StringValueGenerator().generateValue();
      case "Double" -> new DoubleValueGenerator().generateValue();
      case "Short" -> new ShortValueGenerator().generateValue();
      case "Long" -> new LongValueGenerator().generateValue();
      case "Float" -> new FloatValueGenerator().generateValue();
      case "Boolean" -> new BooleanValueGenerator().generateValue();
      default -> new StringValueGenerator().generateValue(); // Default to string if type is unknown
    };
  }

  private void saveToJsonFile(
      String path, String providerName, ArrayList<Map<String, String>> rows) {
    var file = new File(path + File.separator + providerName + ".json");
    file.getParentFile().mkdirs();
    var gson = new GsonBuilder().setPrettyPrinting().create();
    try (var writer = new FileWriter(file, false)) {
      writer.write(gson.toJson(rows));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
