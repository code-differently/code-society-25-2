/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author vscode
 */
@Service
public class JsonFileGenerator {

  Map<Class<?>, ValueGenerator> TYPE_TO_GENERATOR =
      Map.of(
          Integer.class, new IntValueGenerator(),
          String.class, new StringValueGenerator(),
          Double.class, new DoubleValueGenerator(),
          Short.class, new ShortValueGenerator(),
          Long.class, new LongValueGenerator(),
          Float.class, new FloatValueGenerator(),
          Boolean.class, new BooleanValueGenerator());

  // Refrences to all DataProvider beans
  @Autowired private List<DataProvider> dataProviders;

  // overriding the createTestFile method to generate files for each DataProvider
  public void createTestFile(String path) {

    for (DataProvider provider : dataProviders) {

      Map<String, Class> providerFileData = provider.getColumnTypeByName();

      List<ValueGenerator> generators = mapColumnTypeToGenerator(providerFileData);

      ArrayList<Map<String, String>> rows = createSampleData(generators);

      saveToJsonFile(path, provider.getProviderName(), rows);
    }
  }

  public List<ValueGenerator> mapColumnTypeToGenerator(Map<String, Class> providerFileData) {

    ArrayList<ValueGenerator> generators = new ArrayList<>();

    for (int i = 1; i <= providerFileData.size(); i++) {
      String column = "column" + i;
      Class<?> type = providerFileData.get(column);

      // Look up the appropriate generator for this column type.
      ValueGenerator generator = TYPE_TO_GENERATOR.get(type);
      if (generator == null) {
        throw new IllegalArgumentException("No generator found for type: " + type);
      }
      generators.add(generator);
    }

    return generators;
  }

  protected ArrayList<Map<String, String>> createSampleData(List<ValueGenerator> generators) {
    var rows = new ArrayList<Map<String, String>>();
    for (var i = 0; i < 10; ++i) {
      Map<String, String> row = createRow(generators);
      rows.add(createRow(generators));
    }
    return rows;
  }

  protected Map<String, String> createRow(List<ValueGenerator> generators) {
    var row = new LinkedHashMap<String, String>();
    for (int i = 0; i < generators.size(); ++i) {
      var columnIndex = i + 1;
      row.put("column" + columnIndex, generators.get(i).generateValue());
    }
    return row;
  }

  protected void saveToJsonFile(
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
