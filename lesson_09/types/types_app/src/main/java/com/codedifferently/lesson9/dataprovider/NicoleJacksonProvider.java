package com.codedifferently.lesson9.dataprovider;

import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class NicoleJacksonProvider extends DataProvider {
  public String getProviderName() {
    return "nicolejackson";
  }

  public Map<String, Class> getColumnTypeByName() {
    return Map.of(
        "column1", Short.class,
        "column2", Float.class,
        "column3", Double.class,
        "column4", Boolean.class,
        "column5", String.class,
        "column6", Long.class,
        "column7", Integer.class);
  }
}
