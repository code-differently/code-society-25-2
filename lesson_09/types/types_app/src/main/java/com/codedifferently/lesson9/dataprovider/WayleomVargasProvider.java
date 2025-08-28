package com.codedifferently.lesson9.dataprovider;

import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class WayleomVargasProvider extends DataProvider {
  public String getProviderName() {
    return "WayleomVargasProvider";
  }

  public Map<String, Class> getColumnTypeByName() {
    return Map.of(
        "column1", Short.class,
        "column2", Boolean.class,
        "column3", Double.class,
        "column4", String.class,
        "column5", Integer.class,
        "column6", Long.class,
        "column7", Float.class);
  }
}
