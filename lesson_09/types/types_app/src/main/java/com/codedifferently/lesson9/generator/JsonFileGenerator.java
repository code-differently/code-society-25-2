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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author vscode
 */
@Service
public class JsonFileGenerator extends SampleFileGenerator {

  // Refrences to all DataProvider beans
  @Autowired private List<DataProvider> dataProviders;

  // overriding the createTestFile method to generate files for each DataProvider
  @Override
  public void createTestFile(String path, String providerName) {

    for (DataProvider provider : dataProviders) {

      Map<String, Class> providerFileData = provider.getColumnTypeByName();

      List<ValueGenerator> generators = mapColumnTypeToGenerator(providerFileData);

      ArrayList<Map<String, String>> rows = createSampleData(generators);

      saveToJsonFile(path, provider.getProviderName(), rows);
    }
  }

  public void printDataProviderColumns() {

    for (DataProvider provider : dataProviders) {
      System.out.println("Provider: " + provider.getProviderName());
      Map<String, Class> providerFileData = provider.getColumnTypeByName();
      for (Map.Entry<String, Class> entry : providerFileData.entrySet()) {
        String columnName = entry.getKey();
        Class columnType = entry.getValue();
        System.out.println(columnName + " : " + columnType.getSimpleName());
      }
      System.out.println("-----");
    }
  }

  public void printGeneratedColoums() {
    for (DataProvider provider : dataProviders) {
      System.out.println("Provider: " + provider.getProviderName());
      Map<String, Class> providerFileData = provider.getColumnTypeByName();
      List<ValueGenerator> generators = mapColumnTypeToGenerator(providerFileData);
      for (ValueGenerator generator : generators) {
        System.out.println(generator.getClass().getSimpleName());
      }
      System.out.println("-----");
    }
  }

  public List<ValueGenerator> mapColumnTypeToGenerator(Map<String, Class> providerFileData) {

    List<ValueGenerator> generators = new ArrayList<>();
    // Initialize the list with null values to ensure it has 7 elements
    for (int i = 0; i < 7; i++) {
      generators.add(new IntValueGenerator());
    }
    // Map each column type to a corresponding ValueGenerator
    for (Map.Entry<String, Class> entry : providerFileData.entrySet()) {

      Class columnType = entry.getValue();
      String columnName = entry.getKey();
      // Extracts the index from the column name (e.g., "column1" -> 0) to place the generator in
      // the correct position
      Integer columnIndex = columnName.charAt(columnName.length() - 1) - '1';
      System.out.println(columnIndex);
      // Gets the entry value amd checks its type to add the corresponding generator and set the
      // values to its index so the list can be in the correct order
      if (columnType == Integer.class) {
        generators.set(columnIndex, new IntValueGenerator());

      } else if (columnType == String.class) {
        generators.set(columnIndex, new StringValueGenerator());

      } else if (columnType == Double.class) {
        generators.set(columnIndex, new DoubleValueGenerator());

      } else if (columnType == Short.class) {
        generators.set(columnIndex, new ShortValueGenerator());

      } else if (columnType == Long.class) {
        generators.set(columnIndex, new LongValueGenerator());

      } else if (columnType == Float.class) {
        generators.set(columnIndex, new FloatValueGenerator());

      } else if (columnType == Boolean.class) {
        generators.set(columnIndex, new BooleanValueGenerator());
      }
    }

    return generators;
  }
}
