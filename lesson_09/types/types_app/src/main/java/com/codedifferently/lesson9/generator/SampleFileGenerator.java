package com.codedifferently.lesson9.generator;

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
   * Create a test file with sample data.
   *
   * @param path the path to the directory where the file will be created
   * @param providerName the name of the provider
   */
  public void createTestFile(String path, String providerName) {
    ArrayList<Map<String, String>> rows;
    if (providerName.equals("benjaminscottprovider")) {
      // Use fixed data for benjaminscottprovider
      rows = createFixedSampleData();
    } else {
      getShuffledGenerators(); // Shuffle the generators for random order
      rows = createRandomSampleData();
    }
    saveToJsonFile(path, providerName, rows);
  }

  private List<ValueGenerator> getShuffledGenerators() {
    var generators = Arrays.asList(GENERATORS);
    Collections.shuffle(generators);
    return generators;
  }

  private ArrayList<Map<String, String>> createRandomSampleData() {
    var rows = new ArrayList<Map<String, String>>();
    for (var i = 0; i < 10; ++i) {
      var row = new LinkedHashMap<String, String>();
      for (int j = 0; j < GENERATORS.length; ++j) {
        var columnIndex = j + 1;
        row.put("column" + columnIndex, GENERATORS[j].generateValue());
      }
      rows.add(row);
    }
    return rows;
  }

  private ArrayList<Map<String, String>> createFixedSampleData() {
    var rows = new ArrayList<Map<String, String>>();
    for (var i = 0; i < 10; ++i) {
      var row = new LinkedHashMap<String, String>();
      // Match BenjaminScottProvider types: column1=String, column2=Short, column3=Integer,
      // column4=Boolean, column5=Float, column6=Double, column7=Long
      row.put("column1", String.format("string_%d", i)); // String
      row.put("column2", String.valueOf((short) (100 + i))); // Short
      row.put("column3", String.valueOf(1000 + i)); // Integer
      row.put("column4", String.valueOf(i % 2 == 0)); // Boolean
      row.put("column5", String.format("%.1f", 100.0 + i)); // Double
      row.put("column6", String.format("%.1f", 10.0f + i)); // Float
      row.put("column7", String.valueOf(10000L + i)); // Long
      rows.add(row);
    }
    return rows;
  }

  private void saveToJsonFile(
      String path, String providerName, ArrayList<Map<String, String>> rows) {
    var file = new File(path + File.separator + providerName + ".json");
    file.getParentFile().mkdirs();
    var gson = new GsonBuilder().setPrettyPrinting().create();
    try (var writer = new FileWriter(file, false)) {
      writer.write(gson.toJson(rows));
    } catch (IOException e) {
      throw new RuntimeException("Failed to write to file: " + file, e);
    }
  }
}
