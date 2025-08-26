package com.codedifferently.lesson9.dataprovider;

import java.util.Map;
import org.springframework.stereotype.Service;

import com.codedifferently.lesson9.dataprovider.DataProvider;

@Service("JoneeDataFileProvider")
public class JoneeDataFileProvider implements DataProvider {
  public String getProviderName() {
    return "joneeData";
  }

  public Map<String, Class> getColumnTypeByName() {
    return Map.of(
        "column1", Float.class,
        "column2", Double.class,
        "column3", Short.class,
        "column4", String.class,
        "column5", Boolean.class,
        "column6", Long.class,
        "column7", Integer.class);
  }
}
