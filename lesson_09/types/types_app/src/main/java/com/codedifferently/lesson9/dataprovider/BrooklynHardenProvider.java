package com.codedifferently.lesson9.dataprovider;

import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class BrooklynHardenProvider extends DataProvider {
  public String getProviderName() {
    return "brooklynharden";
  }

  public Map<String, Class> getColumnTypeByName() {
    return Map.of(
        "column1", String.class,
        "column2", Short.class,
        "column3", Boolean.class,
        "column4", Double.class,
        "column5", Long.class,
        "column6", Float.class,
        "column7", Integer.class);
  }
}
