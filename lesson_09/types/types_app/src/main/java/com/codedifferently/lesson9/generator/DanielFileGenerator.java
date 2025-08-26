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
public class DanielFileGenerator extends SampleFileGenerator {

  @Autowired private List<DataProvider> dataProviders;

  @Override
  public void createTestFile(String path, String providerName) {
    for (DataProvider provider : dataProviders) {
      Map<String, Class> providerFileData = provider.getColumnTypeByName();
      List<ValueGenerator> generators = mapColumnTypeToGenerator(providerFileData);
      ArrayList<Map<String, String>> rows = createSampleData(generators);
      saveToJsonFile(path, provider.getProviderName(), rows);
    }
  }

  public List<ValueGenerator> mapColumnTypeToGenerator(Map<String, Class> providerFileData) {

    List<ValueGenerator> generators = new ArrayList<>();

    for (Map.Entry<String, Class> entry : providerFileData.entrySet()) {
      Class columnType = entry.getValue();
      if (columnType == Integer.class) {
        generators.add(new IntValueGenerator());

      } else if (columnType == String.class) {
        generators.add(new StringValueGenerator());

      } else if (columnType == Double.class) {
        generators.add(new DoubleValueGenerator());

      } else if (columnType == Short.class) {
        generators.add(new ShortValueGenerator());

      } else if (columnType == Long.class) {
        generators.add(new LongValueGenerator());

      } else if (columnType == Float.class) {
        generators.add(new FloatValueGenerator());

      } else if (columnType == Boolean.class) {
        generators.add(new BooleanValueGenerator());
      }
    }
    return generators;
  }
}
