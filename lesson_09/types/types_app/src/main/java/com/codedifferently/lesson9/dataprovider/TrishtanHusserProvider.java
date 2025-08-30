package com.codedifferently.lesson9.dataprovider;

import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class TrishtanHusserProvider extends DataProvider {
  public String getProviderName() {
    return "Thusser";
  }

  public Map<String, Class> getColumnTypeByName() {
    return Map.of(
        "column1", Long.class,
        "column2", Double.class,
        "column3", Boolean.class,
        "column4", String.class,
        "column5", Short.class,
        "column6", Float.class,
        "column7", Integer.class);
  }
}
