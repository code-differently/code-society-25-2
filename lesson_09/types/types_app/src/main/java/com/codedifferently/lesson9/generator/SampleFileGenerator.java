package com.codedifferently.lesson9.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.codedifferently.lesson9.dataprovider.DataProvider;
import com.codedifferently.lesson9.generator.Generators.BooleanValueGenerator;
import com.codedifferently.lesson9.generator.Generators.DoubleValueGenerator;
import com.codedifferently.lesson9.generator.Generators.FloatValueGenerator;
import com.codedifferently.lesson9.generator.Generators.IntValueGenerator;
import com.codedifferently.lesson9.generator.Generators.LongValueGenerator;
import com.codedifferently.lesson9.generator.Generators.ShortValueGenerator;
import com.codedifferently.lesson9.generator.Generators.StringValueGenerator;
import com.google.gson.GsonBuilder;

/** A class to generate a sample file with random data. */
public class SampleFileGenerator {

  private static final Map<Class<?>, ValueGenerator> GENERATOR_MAP =
      Map.of(
          Integer.class, new IntValueGenerator(),
          String.class, new StringValueGenerator(),
          Double.class, new DoubleValueGenerator(),
          Short.class, new ShortValueGenerator(),
          Long.class, new LongValueGenerator(),
          Float.class, new FloatValueGenerator(),
          Boolean.class, new BooleanValueGenerator());

/**
 * Creates a JSON test file containing sample data for the given provider.
 *
 * @param path - The directory path where the JSON file should be stored.
 * @param provider - The DataProvider instance containing column definitions and metadata.
 * @param mode - Determines how data is generated.
 *    - "single": Uses a shuffled/random set of value generators.
 *    - "bulk": Uses a strict ordered set of value generators matching schema.
 * @throws IllegalArgumentException If mode is not "single" or "bulk".
 */ 
  public void createTestFile(String path, DataProvider provider, String mode) {
    List<ValueGenerator> generators;
    if (null == mode.toLowerCase()) {
      throw new IllegalArgumentException("Mode must be 'single' or 'bulk'");
    } else
      switch (mode.toLowerCase()) {
        case "single" -> generators = getShuffledGenerators();
        case "bulk" -> generators = getOrderedGenerators(provider);
        default -> throw new IllegalArgumentException("Mode must be 'single' or 'bulk'");
      }

    String providerName = provider.getClass().getSimpleName().replace("Provider", "").toLowerCase();
    ArrayList<Map<String, String>> rows = createSampleData(provider, generators);
    saveToJsonFile(path, providerName, rows);
  }

  private List<ValueGenerator> getShuffledGenerators() {
    var generators = new ArrayList<>(GENERATOR_MAP.values());
    Collections.shuffle(generators);
    return generators;
  }


  /**
 * Returns value generators in the order defined by the provider’s schema.
 *
 * @param provider - The DataProvider instance with schema info.
 * @returns A list of generators matching the schema order.
 * @throws IllegalArgumentException If no generator exists for a column type.
 */
  private List<ValueGenerator> getOrderedGenerators(DataProvider provider) {
    List<ValueGenerator> ordered = new ArrayList<>();
    provider
        .getColumnTypeByName()
        .forEach(
            (col, clazz) -> {
              ValueGenerator gen = GENERATOR_MAP.get(clazz);
              if (gen == null) {
                throw new IllegalArgumentException("No generator found for type: " + clazz);
              }
              ordered.add(gen);
            });
    return ordered;
  }

  /**
 * Generates 10 rows of sample data for a provider.
 *
 * @param provider - The DataProvider containing column definitions.
 * @param generators - The list of generators to use for each column.
 * @returns A list of maps where each map is a row (column → value).
 */
  private ArrayList<Map<String, String>> createSampleData(
      DataProvider provider, List<ValueGenerator> generators) {
    var rows = new ArrayList<Map<String, String>>();
    for (int i = 0; i < 10; ++i) {
      rows.add(createRow(provider, generators));
    }
    return rows;
  }

  private Map<String, String> createRow(DataProvider provider, List<ValueGenerator> generators) {
    var row = new LinkedHashMap<String, String>();
    List<String> columnNames = new ArrayList<>(provider.getColumnTypeByName().keySet());

    for (int i = 0; i < columnNames.size(); i++) {
      row.put(columnNames.get(i), generators.get(i).generateValue());
    }
    return row;
  }

  private void saveToJsonFile(String path, String providerName, ArrayList<Map<String, String>> rows) {
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
