package com.codedifferently.lesson9.dataprovider;

import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class MarySueProvider extends DataProvider {
  public String getProviderName() {
    return "marysue";
  }

  public Map<String, Class> getColumnTypeByName() {
    return Map.of(
        "column1", Long.class,
        "column2", Integer.class,
        "column3", Short.class,
        "column4", String.class,
        "column5", Double.class,
        "column6", Boolean.class,
        "column7", Float.class);
  }
}
