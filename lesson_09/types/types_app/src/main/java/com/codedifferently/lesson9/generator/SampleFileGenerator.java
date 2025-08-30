package com.codedifferently.lesson9.generator;

import com.codedifferently.lesson9.dataprovider.DataProvider;
import com.codedifferently.lesson9.generator.Generators.BooleanValueGenerator;
import com.codedifferently.lesson9.generator.Generators.DoubleValueGenerator;
import com.codedifferently.lesson9.generator.Generators.FloatValueGenerator;
import com.codedifferently.lesson9.generator.Generators.IntValueGenerator;
import com.codedifferently.lesson9.generator.Generators.LongValueGenerator;
import com.codedifferently.lesson9.generator.Generators.ShortValueGenerator;
import com.codedifferently.lesson9.generator.Generators.StringValueGenerator;
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

/** A class to generate a sample file with random data. */
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
   * Create a test file with random sample data. This method uses shuffled generators to create
   * random data types for each column. Preserved for backward compatibility.
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
   * Create a test file with sample data that matches the DataProvider's expected types. This is the
   * enhanced version that ensures test compatibility by generating data according to the provider's
   * type specifications.
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

  /**
   * Generate a file for a specific DataProvider. This method handles the complete file generation
   * process including path setup.
   *
   * @param path the path to the directory where the file will be created
   * @param provider the DataProvider to generate data for
   */
  public void generateSampleFileForProvider(String path, DataProvider provider) {
    // Use the provider-aware method to ensure correct data types
    createTestFileForProvider(path, provider);
    System.out.println("Generated file for provider: " + provider.getProviderName());
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
