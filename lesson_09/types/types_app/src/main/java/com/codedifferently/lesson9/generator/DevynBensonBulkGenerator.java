
package com.codedifferently.lesson9.generator;

import com.codedifferently.lesson9.dataprovider.DataProvider;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

/** A class to generate a sample file with random data. */
public class DevynBensonBulkGenerator {

    @Autowired
    private List<DataProvider> dataProviders;

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
   * Create a test file with sample data.
   *
   * @param path the path to the directory where the file will be created
   * @param providerName the name of the provider
   */
  public void createTestFileForEveryProvider(String path, List<DataProvider> dataProviders) {

    for (var dataProvider : dataProviders) {
      var generators = getShuffledGenerators();
      ArrayList<Map<String, String>> rows = createSampleData(generators);
      saveToJsonFile(path, dataProvider.getProviderName(), rows);
    }
  }
  // Mapping of classes to value generators
  private static final Map<Class<?>, ValueGenerator> TYPE_TO_GENERATOR =
      Map.of(
          Integer.class, new IntValueGenerator(),
          Short.class, new ShortValueGenerator(),
          Long.class, new LongValueGenerator(),
          Float.class, new FloatValueGenerator(),
          Boolean.class, new BooleanValueGenerator(),
          String.class, new StringValueGenerator(),
          Double.class, new DoubleValueGenerator());

  /**
   * Creates a JSON provider file with generated sample data.
   *
   * @param path the file path where the provider file will be written
   * @param providerName the name of the provider
   * @param provider the {DataProvider} that defines columns
   * @throws IllegalArgumentException if no generator is found for a column type
   */
  public void createProviderFile(String path, String providerName, DataProvider provider) {
    var generators = getGenerators(provider);
    ArrayList<Map<String, String>> rows = createSampleData(generators);

    // Writes the generated rows to a JSON file.
    saveToJsonFile(path, providerName, rows);
  }
  private List<ValueGenerator> getGenerators(DataProvider provider) {
    Map<String, Class> columnTypes = provider.getColumnTypeByName();
    ArrayList<ValueGenerator> generators = new ArrayList<>();

    for (int i = 1; i <= columnTypes.size(); i++) {
      String column = "column" + i;
      Class<?> type = columnTypes.get(column);

      // Look up the appropriate generator for this column type.
      ValueGenerator generator = TYPE_TO_GENERATOR.get(type);
      if (generator == null) {
        throw new IllegalArgumentException("No generator found for type: " + type);
      }
      generators.add(generator);
    }

    return generators;
  }

  private List<ValueGenerator> getdGenerators(DataProvider provider) {
    var generators = Arrays.asList(GENERATORS);
    Collections.shuffle(generators);
    return generators;
  }

  private ArrayList<Map<String, String>> createSampleData(List<ValueGenerator> generators) {
    var rows = new ArrayList<Map<String, String>>();
    for (var i = 0; i < 10; ++i) {
      Map<String, String> row = createRow(generators);
      rows.add(createRow(generators));
    }
    return rows;
  }

  private Map<String, String> createRow(List<ValueGenerator> generators) {
    var row = new LinkedHashMap<String, String>();
    for (int i = 0; i < GENERATORS.length; ++i) {
      var columnIndex = i + 1;
      row.put("column" + columnIndex, GENERATORS[i].generateValue());
    }
    return row;
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
