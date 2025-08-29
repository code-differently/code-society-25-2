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
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/** A class to bulk generate sample files with random data for all DataProvider implementations. */
public class BulkSampleFileGenerator {

  private static final Map<Class<?>, ValueGenerator> GENERATOR_BY_TYPE =
      Map.of(
          Integer.class, new IntValueGenerator(),
          String.class, new StringValueGenerator(),
          Double.class, new DoubleValueGenerator(),
          Short.class, new ShortValueGenerator(),
          Long.class, new LongValueGenerator(),
          Float.class, new FloatValueGenerator(),
          Boolean.class, new BooleanValueGenerator());

  /**
   * Create test files with sample data for all provided DataProvider implementations.
   *
   * @param path the path to the directory where the files will be created
   * @param dataProviders the collection of all DataProvider implementations
   */
  public void createBulkTestFiles(String path, Collection<DataProvider> dataProviders) {
    System.out.println("Found " + dataProviders.size() + " DataProvider implementations:");

    for (DataProvider provider : dataProviders) {
      System.out.println("- Generating sample file for: " + provider.getProviderName());
      createTestFileForProvider(path, provider);
    }

    System.out.println("Bulk generation completed! Files saved to: " + path);
  }

  /**
   * Create a test file with sample data for a specific DataProvider.
   *
   * @param path the path to the directory where the file will be created
   * @param provider the DataProvider implementation
   */
  private void createTestFileForProvider(String path, DataProvider provider) {
    Map<String, Class> columnTypeByName = provider.getColumnTypeByName();

    System.out.println("  Column specifications for " + provider.getProviderName() + ":");
    columnTypeByName.forEach(
        (column, type) -> System.out.println("    - " + column + ": " + type.getSimpleName()));

    ArrayList<Map<String, String>> rows = createSampleDataForProvider(columnTypeByName);
    saveToJsonFile(path, provider.getProviderName(), rows);
  }

  /**
   * Create sample data rows based on the column type mapping from a DataProvider.
   *
   * @param columnTypeByName mapping of column names to their Java types
   * @return list of sample data rows
   */
  private ArrayList<Map<String, String>> createSampleDataForProvider(
      Map<String, Class> columnTypeByName) {
    var rows = new ArrayList<Map<String, String>>();
    for (var i = 0; i < 10; ++i) {
      Map<String, String> row = createRowForProvider(columnTypeByName);
      rows.add(row);
    }
    return rows;
  }

  /**
   * Create a single row of sample data based on column type mapping.
   *
   * @param columnTypeByName mapping of column names to their Java types
   * @return a single sample data row
   */
  private Map<String, String> createRowForProvider(Map<String, Class> columnTypeByName) {
    var row = new LinkedHashMap<String, String>();
    for (Map.Entry<String, Class> entry : columnTypeByName.entrySet()) {
      String columnName = entry.getKey();
      Class<?> columnType = entry.getValue();

      ValueGenerator generator = GENERATOR_BY_TYPE.get(columnType);
      if (generator != null) {
        row.put(columnName, generator.generateValue());
      } else {
        System.err.println(
            "Warning: No generator found for type "
                + columnType.getName()
                + " in column "
                + columnName
                + ". Using default string value.");
        row.put(columnName, "unknown_type_" + columnType.getSimpleName());
      }
    }
    return row;
  }

  /**
   * Save the sample data to a JSON file.
   *
   * @param path the directory path
   * @param providerName the name of the provider (used as filename)
   * @param rows the sample data rows
   */
  private void saveToJsonFile(
      String path, String providerName, ArrayList<Map<String, String>> rows) {
    var file = new File(path + File.separator + providerName + ".json");
    file.getParentFile().mkdirs();
    var gson = new GsonBuilder().setPrettyPrinting().create();
    try (var writer = new FileWriter(file, false)) {
      writer.write(gson.toJson(rows));
      System.out.println("  ✓ Generated: " + file.getName());
    } catch (IOException e) {
      System.err.println("  ✗ Failed to generate file for " + providerName + ": " + e.getMessage());
    }
  }
}